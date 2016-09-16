# AppDirect

Feature of this application

This appliaction is implementing following Rest Apis for Subscriber

  1. Create Subscriber :-
      Url = http://ip:port/subscription/create/?eventUrl=https://www.acme-marketplace.com/api/integration/v1/events/12345
  
  2. Update Subscriber :-
      Url = http://ip:port/subscription/change/?eventUrl=https://www.acme-marketplace.com/api/integration/v1/events/12345
  
  3. Delete Subscriber :-   
      Url = http://ip:port/subscription/cancel/?eventUrl=https://www.acme-marketplace.com/api/integration/v1/events/12345
      

This application also trigger Rest request for event that is given in eventUrl Query Parameter.

This application using cache to store Subscriber data (No Cassandra and SQL).


*******************Installation Guide********************

This Application is using embeddded jetty 9 for creating server instance.
All Configuration should be checked in system_setting.properties file in configuration folder.

*********************************************************
