## Project Description 
This is the Project 1 for COMP90015 Distributed Systems. <br>
This project focuses on building a multi-threaded program in java, utilising <br>
TCP protocol and GUI implimentation. <br>
Both client side(required) and server side(innovation part) GUI are implemented using Java Swing. 
## Install and Run
To run the server.jar and client.jar, simply use command:<br>
```
java –jar DictionaryServer.jar <port> <dictionary-file>
```
Where [port] is the port number where the server will listen for incoming client connections
and <dictionary-file> is the path to the file containing the initial dictionary data.<br>
```
java –jar DictionaryClient.jar <server-address> <server-port>
```
When the client is launched, it creates a TCP socket bound to the server address and port number. This socket remains open for the duration of the client-server interaction. All messages are sent/received through this socket.
## Functional Requirements
### Query the meaning of a given word
The client should implement a function that is used to query the dictionary with the following
minimum (additional input/output parameters can be used as required) input and output:
Input: Word to search
Output: Meaning(s) of the word
Error: The client should clearly indicate if the word was not found or if an error occurred. In case of an error, a suitable description of the error should be given to the user.
### Add a new word
Add a new word and one or more of its meanings to the dictionary. For the word to be added successfully it should not exist already in the dictionary. Also, attempting to add a word without an associated meaning should result in an error. A new word added by one client should be visible to all other clients of the dictionary server. The minimum input and output parameters are as follows:
Input: Word to add, meaning(s)
Output: Status of the operation (e.g., success, duplicate)
Error: The user should be informed if any errors occurred while performing the operation.
### Remove an existing word

Remove a word and all of its associated meanings from the dictionary. A word deleted by one client should not be visible to any of the clients of the dictionary server. If the word does not exist in the dictionary then no action should be taken. The minimum input and output parameters are as follows:
Input: Word to remove
Output: Status of the operation (e.g., success, not found)
          
Error: The user should be informed if any errors occurred while performing the operation.