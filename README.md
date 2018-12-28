# shortestpathapplication
Application to find the shortest path for a graph


Startup command: java -jar -Dport=<port number> -Dspring.dataload.excelfile.name="<Excel file path for data load>" <Application war filename>.war

http://<hostname>:<port number>/getShortPath?source=<Source Node>&destination=<Destination Node>
