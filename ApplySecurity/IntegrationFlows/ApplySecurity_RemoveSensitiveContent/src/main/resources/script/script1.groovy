/*
 The integration developer needs to create the method processData 
 This method takes Message object of package com.sap.gateway.ip.core.customdev.util 
which includes helper methods useful for the content developer:
The methods available are:
    public java.lang.Object getBody()
	public void setBody(java.lang.Object exchangeBody)
    public java.util.Map<java.lang.String,java.lang.Object> getHeaders()
    public void setHeaders(java.util.Map<java.lang.String,java.lang.Object> exchangeHeaders)
    public void setHeader(java.lang.String name, java.lang.Object value)
    public java.util.Map<java.lang.String,java.lang.Object> getProperties()
    public void setProperties(java.util.Map<java.lang.String,java.lang.Object> exchangeProperties) 
    public void setProperty(java.lang.String name, java.lang.Object value)
    public java.util.List<com.sap.gateway.ip.core.customdev.util.SoapHeader> getSoapHeaders()
    public void setSoapHeaders(java.util.List<com.sap.gateway.ip.core.customdev.util.SoapHeader> soapHeaders) 
       public void clearSoapHeaders()
 */
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData(Message message) {
       def messageLog = messageLogFactory.getMessageLog(message)
       
       // Log Headers 
       def map = message.getHeaders();
       
       // Log only needed headers (white list) 
       messageLog.setStringProperty("Content-Type (whitelist)",map.get("Content-Type"))
       messageLog.setStringProperty("Accept (whitelist)",map.get("Accept"))
       messageLog.setStringProperty("Context (whitelist)",map.get("Context"))
       
       // exclude sensitive headers (black list)    
       map.each{ k, v -> if (!k.equals("authorization"))  messageLog.setStringProperty(k+" (blacklist)", v) }
       
       return message;
}