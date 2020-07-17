package com.codestudioworks.clienteventmanager;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;

class ClientXMLTableRowData {

  String XML_TD01_Gruppo = null;
  String XML_TD02_Categoria = null;
  String XML_TD03_Codice = null;
  String XML_TD04_Ragione_Sociale = null;
  String XML_TD05_2__Ragione_Sociale = null;
  String XML_TD06_Indirizzo = null;
  String XML_TD07_Cap = null;
  String XML_TD08_Citta = null;
  String XML_TD09_Prov = null;
  String XML_TD10_Stato = null;
  String XML_TD11_PtaIva = null;
  String XML_TD12_Cod_Fisc = null;
  String XML_TD13_Telefono = null;
  String XML_TD14_Fax = null;
  String XML_TD15_Cellulare = null;
  String XML_TD16_Email = null;
  String XML_TD17_Pagamento = null;
  String XML_TD18_Banca = null;
  String XML_TD19_Abi = null;
  String XML_TD20_Cab = null;
  String XML_TD21_Agente = null;
  String XML_TD22_Annullato = null;
  String XML_TD23_Zona = null;
  String XML_TD24_Referente = null;
  String XML_TD25_Note = null;
  String XML_TD26_Note_commerciali = null;
  String XML_TD27_Codalfa = null;
  String XML_TD28_Regione = null;
  String XML_TD29_Coordinate = null;
  String XML_TD30_Data_Creazione = null;
  String XML_TD31_Codice_Iva = null;
  String XML_TD32_Aliquota_Iva = null;
  String XML_TD33_Settori_EA = null;

}

class ElatosConnection {

  private static DB db = null;
  private static final String sessionCookieNameStart = "ASPSESSIONID";
  private static String sessionCookieName = null;
  private static String sessionId = null;

  static void setDatabase(DB db) {
    ElatosConnection.db = db;
  }

  private static String getCurrentDateTime() {

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH.mm.ss");
    return df.format(new Date());

  }

  private static final String enc(String s) {

    String sEncoded = null;

    try {

      sEncoded = URLEncoder.encode(s, "UTF-8");

    } catch (UnsupportedEncodingException e) {

      e.printStackTrace();

    }

    return sEncoded;

  }
  
  private static void logResponse(HttpURLConnection conn) {
    
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line = reader.readLine();
      while (line != null) {
        System.out.println(line);
        line = reader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  private static void setConnOptions(HttpURLConnection conn) {
    
    conn.setUseCaches(false);
    conn.setDoInput(true);
    conn.setDoOutput(true);
    conn.setInstanceFollowRedirects(false); // important
    conn.setRequestProperty("Connection", "keep-alive");

  }

  // connect and retrieve and set the session id name and value
  
  private static boolean doRetrieveSessionIdNameAndValue() {

    boolean retVal = true;
    
    HttpURLConnection conn = null;

    try {
    
      URL url = new URL("http://www.elatos.net/");
      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      
      setConnOptions(conn);

      conn.getResponseCode(); // should return a 302 (Temporary Redirect)
      Map<String, List<String>> responseHeaders = conn.getHeaderFields();
      List<String> cookieHeaderValues = responseHeaders.get("Set-Cookie");
      for (String cookieHeaderValue : cookieHeaderValues)
        if (cookieHeaderValue.regionMatches(0, sessionCookieNameStart, 0, sessionCookieNameStart.length())) {
          int equalsIndex = cookieHeaderValue.indexOf('=');
          if (equalsIndex == -1) { retVal = false; break; }
          ElatosConnection.sessionCookieName = cookieHeaderValue.substring(0, equalsIndex);
          int semicolonIndex = cookieHeaderValue.indexOf(';');
          ElatosConnection.sessionId = cookieHeaderValue.substring(sessionCookieName.length() + 1, semicolonIndex);
        }
      
    } catch (IOException e) {
      
      e.printStackTrace();
      
      retVal = false;
      
    }

    if (conn != null)
      
      conn.disconnect();
    
    return retVal;

  }
  
  // log into CRM using session information and supplied login credentials
  
  private static boolean doLogin(String businessName, String userName, String password) {
    
    boolean retVal = true;
    
    HttpURLConnection conn = null;

    try {
    
      URL url = new URL("http://www.elatos.net/elatos.asp?sez=0");
      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("POST");

      setConnOptions(conn);

      conn.setRequestProperty("Host", "www.elatos.net");
      conn.setRequestProperty("Cookie", sessionCookieName + '=' + sessionId);

      DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
      dos.writeBytes("Az=" + enc(businessName) + "&User=" + enc(userName) + "&Pass=" + enc(password) + "&Submit=ACCEDI");
      dos.flush();

      conn.getResponseCode(); // should return a 302 (Temporary Redirect)
      
      //logResponse(conn);

    } catch (IOException e) {
      
      e.printStackTrace();
      
      retVal = false;
      
    }
    
    if (conn != null)

      conn.disconnect();
    
    return retVal;
  
  }
    
  static boolean doRetrieveClientsXML() {

    boolean retVal = true;
    
    HttpURLConnection conn = null;

    try {
    
      URL url = new URL("http://www.elatos.net/CLIENTIxml.asp");
      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      setConnOptions(conn);

      conn.setRequestProperty("Host", "www.elatos.net");
      conn.setRequestProperty("Cookie", sessionCookieName + '=' + sessionId);
  
      if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
        
        retVal = false;
        
      }
    
      parseClientsXMLFileBatchInsert(conn.getInputStream());
      
    } catch (IOException e) {
      
      e.printStackTrace();
      
      retVal = false;
      
    }
    
    if (conn != null)

      conn.disconnect();
    
    return retVal;

  }
  
  static final String ELEMENT_TABLE = "table";
  static final String ELEMENT_TR = "tr";
  static final String ELEMENT_TD = "td";

  static boolean parseClientsXMLFileBatchInsert(InputStream is) {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    DocumentBuilder db = null;

    try {

      db = dbf.newDocumentBuilder();

    } catch (ParserConfigurationException e) {

      e.printStackTrace();

      return false;

    }

    Document doc = null;

    try {

      doc = db.parse(is);

    } catch (SAXException e) {

      System.out.println("Client XML file is not well formed.");

      e.printStackTrace();

      return false;

    } catch (IOException e) {

      e.printStackTrace();

      return false;

    }

    // retrieve and store client data from XML file

    NodeList nlTable = doc.getChildNodes();

    if (nlTable.getLength() == 1) {

      Node nodeTable = nlTable.item(0);

      if (nodeTable.getNodeType() == Node.ELEMENT_NODE && nodeTable.getNodeName().equals(ELEMENT_TABLE)) {

        Element tableElement = (Element) nodeTable;

        NodeList nlTr = tableElement.getChildNodes();

        long tableRowCount = 0; // used to skip first table row which contains table headings

        for (int i = 0; i < nlTr.getLength(); i++) {

          Node nodeTr = nlTr.item(i);

          if (nodeTr.getNodeType() == Node.ELEMENT_NODE && nodeTr.getNodeName().equals(ELEMENT_TR) && tableRowCount++ > 0) {

            Element trElement = (Element) nodeTr;

            NodeList nlTd = trElement.getChildNodes();

            ClientXMLTableRowData clientXMLTableRowData = new ClientXMLTableRowData();

            int tableDataCount = 0; // used to count table data elements

            for (int j = 0; j < nlTd.getLength(); j++) {

              Node nodeTd = nlTd.item(j);

              if (nodeTd.getNodeType() == Node.ELEMENT_NODE && nodeTd.getNodeName().equals(ELEMENT_TD)) {

                Element tdElement = (Element) nodeTd;

                switch (++tableDataCount) {

                  case 1:

                    clientXMLTableRowData.XML_TD01_Gruppo = tdElement.getTextContent();

                    break;

                  case 2:

                    clientXMLTableRowData.XML_TD02_Categoria = tdElement.getTextContent();

                    break;

                  case 3:

                    clientXMLTableRowData.XML_TD03_Codice = tdElement.getTextContent();

                    break;

                  case 4:

                    clientXMLTableRowData.XML_TD04_Ragione_Sociale = tdElement.getTextContent();
                    
                    //System.out.println(clientXMLTableRowData.XML_TD04_Ragione_Sociale);

                    break;

                  case 5:

                    clientXMLTableRowData.XML_TD05_2__Ragione_Sociale = tdElement.getTextContent();

                    break;

                  case 6:

                    clientXMLTableRowData.XML_TD06_Indirizzo = tdElement.getTextContent();

                    break;

                  case 7:

                    clientXMLTableRowData.XML_TD07_Cap = tdElement.getTextContent();

                    break;

                  case 8:

                    clientXMLTableRowData.XML_TD08_Citta = tdElement.getTextContent();

                    break;

                  case 9:

                    clientXMLTableRowData.XML_TD09_Prov = tdElement.getTextContent();

                    break;

                  case 10:

                    clientXMLTableRowData.XML_TD10_Stato = tdElement.getTextContent();

                    break;

                  case 11:

                    clientXMLTableRowData.XML_TD11_PtaIva = tdElement.getTextContent();

                    break;

                  case 12:

                    clientXMLTableRowData.XML_TD12_Cod_Fisc = tdElement.getTextContent();

                    break;

                  case 13:

                    clientXMLTableRowData.XML_TD13_Telefono = tdElement.getTextContent();

                    break;

                  case 14:

                    clientXMLTableRowData.XML_TD14_Fax = tdElement.getTextContent();

                    break;

                  case 15:

                    clientXMLTableRowData.XML_TD15_Cellulare = tdElement.getTextContent();

                    break;

                  case 16:

                    clientXMLTableRowData.XML_TD16_Email = tdElement.getTextContent();

                    break;

                  case 17:

                    clientXMLTableRowData.XML_TD17_Pagamento = tdElement.getTextContent();

                    break;

                  case 18:

                    clientXMLTableRowData.XML_TD18_Banca = tdElement.getTextContent();

                    break;

                  case 19:

                    clientXMLTableRowData.XML_TD19_Abi = tdElement.getTextContent();

                    break;

                  case 20:

                    clientXMLTableRowData.XML_TD20_Cab = tdElement.getTextContent();

                    break;

                  case 21:

                    clientXMLTableRowData.XML_TD21_Agente = tdElement.getTextContent();

                    break;

                  case 22:

                    clientXMLTableRowData.XML_TD22_Annullato = tdElement.getTextContent();

                    break;

                  case 23:

                    clientXMLTableRowData.XML_TD23_Zona = tdElement.getTextContent();

                    break;

                  case 24:

                    clientXMLTableRowData.XML_TD24_Referente = tdElement.getTextContent();

                    break;

                  case 25:

                    clientXMLTableRowData.XML_TD25_Note = tdElement.getTextContent();

                    break;

                  case 26:

                    clientXMLTableRowData.XML_TD26_Note_commerciali = tdElement.getTextContent();

                    break;

                  case 27:

                    clientXMLTableRowData.XML_TD27_Codalfa = tdElement.getTextContent();

                    break;

                  case 28:

                    clientXMLTableRowData.XML_TD28_Regione = tdElement.getTextContent();

                    break;

                  case 29:

                    clientXMLTableRowData.XML_TD29_Coordinate = tdElement.getTextContent();

                    break;

                  case 30:

                    clientXMLTableRowData.XML_TD30_Data_Creazione = tdElement.getTextContent();

                    break;

                  case 31:

                    clientXMLTableRowData.XML_TD31_Codice_Iva = tdElement.getTextContent();

                    break;

                  case 32:

                    clientXMLTableRowData.XML_TD32_Aliquota_Iva = tdElement.getTextContent();

                    break;

                  case 33:

                    clientXMLTableRowData.XML_TD33_Settori_EA =tdElement.getTextContent();

                    break;

                  default:

                    //System.out.println(tableDataCount);
                    //System.out.println(tdElement.getTextContent());
                    System.out.println("Clients XML file contains bad row length.");

                    return false;

                }

              }

            }
            
            insertClientRow(clientXMLTableRowData);

          }

        }

      } else {

        System.out.println("Clients XML file root element is not table element.");

        return false;

      }

    } else {

      System.out.println("Clients XML file does not start with one root element.");

      return false;

    }

    return true;

  }

  static void insertClientRow(ClientXMLTableRowData clientXMLTableRowData) {
    
    Cursor ci = db.database.query(DBHelper.T_CLIENTS, null, DBHelper.C_CLIENTS_COMPANYNAME + " = ?",
      new String[] { clientXMLTableRowData.XML_TD04_Ragione_Sociale + " " + clientXMLTableRowData.XML_TD05_2__Ragione_Sociale }, null, null, null);
    if (!ci.moveToNext()) {
      ContentValues values = new ContentValues();
      values.put(DBHelper.C_CLIENTS_COMPANYNAME, clientXMLTableRowData.XML_TD04_Ragione_Sociale + " " + clientXMLTableRowData.XML_TD05_2__Ragione_Sociale);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSCITY, clientXMLTableRowData.XML_TD08_Citta);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSPROVINCE, clientXMLTableRowData.XML_TD09_Prov);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSCOUNTRY, clientXMLTableRowData.XML_TD10_Stato);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICETAXREGISTRATIONNUMBER, clientXMLTableRowData.XML_TD11_PtaIva);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICETAXPAYERNUMBER, clientXMLTableRowData.XML_TD12_Cod_Fisc);
      values.put(DBHelper.C_CLIENTS_MAINPERSONINCHARGEPHONE, clientXMLTableRowData.XML_TD13_Telefono);
      values.put(DBHelper.C_CLIENTS_MAINPERSONINCHARGEFAX, clientXMLTableRowData.XML_TD14_Fax);
      values.put(DBHelper.C_CLIENTS_MAINPERSONINCHARGECELL, clientXMLTableRowData.XML_TD15_Cellulare);
      values.put(DBHelper.C_CLIENTS_MAINPERSONINCHARGEEMAIL, clientXMLTableRowData.XML_TD16_Email);
      values.put(DBHelper.C_CLIENTS_MAINPERSONINCHARGENAME, clientXMLTableRowData.XML_TD21_Agente);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSAREA, clientXMLTableRowData.XML_TD23_Zona);
      values.put(DBHelper.C_CLIENTS_NOTES, clientXMLTableRowData.XML_TD25_Note);
      values.put(DBHelper.C_CLIENTS_CODE, clientXMLTableRowData.XML_TD27_Codalfa);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSREGION, clientXMLTableRowData.XML_TD28_Regione);
      db.database.insertOrThrow(DBHelper.T_CLIENTS, null, values);
    }
    ci.close();
    
  }
  
  static boolean connect(String businessName, String userName, String password) {
    
    if (businessName == null || userName == null || password == null)
      
      return false;
    
    doRetrieveSessionIdNameAndValue();
    
    return doLogin(businessName, userName, password);
    
  }

}
