# Clack
Clack _(Clarkson Slack)_ project for CS-242.

By Humaira Rezaie and Christopher Mahoney.

## Part 4
### TODO list:
No todo list this time

### Report

> In your write-up explain why you need two new classes for threading.

The client and the server both have 2 separate jobs. They need to read incoming data and send outgoing data.
We can no longer guarantee we're going to send _and then_ receive data, both tasks are going to happen independently.
To support these two new tasks we create two new classes. These also have to happen concurrently, so they are threaded.

> In your write-up, explain why there should be a separate class to receive data from the
server and print it, and the client only gets data from the user and sends it to the server.
Also, why is the class called a ‘listener’?

In reverse order, this new class is a listener because its only task is to receive data from the server then print.
In other words it's _listening_ to the server. Like we explained in the previous question this needs to be a separate
class because reading + sending is now independent of receiving + printer. The new class facilitates adding this feature.

> In your write-up, explain why you need a separate thread for each client, and why you
cannot handle all clients in the main server thread. Conceptually, why is the listener
class ‘ClientSideServerListener’ different from the class ‘ServerSideClientIO’?

Receiving data from a socket is a _blocking_ call. If we handled all clients in the main server thread we could only
listen to one at a time. In the best case this is very inefficient software that wastes a lot of time waiting and is a
bad experience. Worst case we could actually miss that a client sent us data which make the software unreliable.

Conceptually, the listener class `ClientSideServerListener` is different from the `ServerSideClientIO` class because
the listener is "the client listening to the server" while the clientIO is the "server exchanging data the client".

Something we would like to note is this application could be written with 2 threads per client. One thread which handles
inputs from a client and one thread that handles outputs. Writing data to the client is also blocking, a poorly 
performing or a poorly behaving client could create a lot of latency for the other clients in the current model.

> In your write-up, explain why the broadcast() and remove() methods are synchronized.
You may find it easier to answer this question after completing all programming.

Fairly simple, `broadcast()` sends a message to each client `remove()` removes a client from the broadcast list.
If these happen non-atomically `broadcast` could be loop into a client that no longer exists and errors will occur.
The same logic holds with our synchronized new method `listUsers()`, the user list could change while we're reading it.

> In your write-up, discuss all new methods and new code in existing
methods that you wrote to handle LISTUSERS

We did the more difficult task where only the client that asked recieves the user list

We created a new class `ListUsersClackData`. When a client sends a `ListUsersClackData` message the 
`ServerSideClientIO` calls `server.listUsers(client)` which creates the user list `ArrayList<String> usernames`. 
Then we create a new `ListUsersClackData` which is sent to the client. 

In `ServerSideClientIO` we suppress the normal call to `broadcast()`.

We know the usernames of clients by tracking whatever username that was last used. This means clients could potentially
change username whenever they like. Security implications doesn't seem like a big priority in the project. We'll leave
it at that with a comment "we'll change it easily if you'd like to see different behavior".

The "username" field of LISTUSERS isn't very helpful. The client includes their username as normal and when the server
creates a `ListUsersClackData` it's given the uncreative username `"SERVER"`.

The client displays the result of `LISTUSERS` like this:

```
LISTUSERS
Fri Nov 26 17:18:07 EST 2021 | SERVER :
[mahonec, test]
```

That's it!

## Part 3
### TODO list:
- [x] Make ClackData Serializable
- [x] ClackClient for Single Client Communication with Server
- [x] ObjectInputStream and ObjectOutputStream instance variables in ClackClient
- [x] The start() method in ClackClient
- [x] The sendData() method in ClackClient
- [x] The receiveData() method in ClackClient
- [x] ClackServer class for Single Client Communication with Server
- [x] ObjectInputStream and ObjectOutputStream, and ClackData instance variables in
  ClackServer
- [x] The start() method in ClackServe
- [x] The receiveData() method in ClackServer
- [x] The sendData() method in ClackServer
- [x] IllegalArgumentException in ClackServer constructor
- [x] Main method in ClackClient
- [x] Main method in ClackServer
- [x] Create JAR files
- [x] Running the ClackClient and ClackServer programs
- [x] Javadoc comments, and Javadoc folder
- [ ] Contributions reports

## Part 2
### TODO list:
- [x] Encryption and decryption methods
- [x] Implementation of new constructor in MessageClackData
- [x] Implementation of overloaded method getData in two MessageClackData and FileClackData classes
- [x] Implementation of overloaded methods readFileContents and writeFileContents in FileClackData
- [x] New functionality in ClackClient
- [x] Correctly written and running TestClackData
- [x] Correctly written and running TestClackClient
- [x] Report with output from TestClackClient
- [x] Javadoc comments, and Javadoc folder
- [ ] Contributions reports

### Report
For encryption, we used a slightly more complicated scheme. Instead of only being able to encode and decode english 
letters we can support all 2^16 - 1 `char`s. The underlying algorithm is basically the same.

In TestClarkData we created some files in `/tmp`. Depending on your system this may not work for you.

```
$ LISTUSERS
$ SENDFILE /Users/mahonec/IdeaProjects/Clack/src/test/Part2_document.txt
Fri Oct 15 00:07:16 EDT 2021 | user :
A digital computer can usually be regarded as consisting of three parts: (i) Store. (ii) Executive unit. (iii) Control. ...The executive unit is the part which carries out the various individual operations involved in a calculation. ...It is the duty of the control to see that...[the table of] instructions are obeyed correctly and in the right order. ...A typical instruction might say—"Add the number stored in position 6809 to that in 4302 and put the result back into the latter storage position." Needless to say it would not occur in the machine expressed in English. It would more likely be coded in a form such as 6809430217. Here 17 says which of various possible operations [add] is to be performed on the two numbers. ...It will be noticed that the instruction takes up 10 digits and so forms one packet of information...
$ Hello world ! :)
Fri Oct 15 00:07:34 EDT 2021 | user :
Hello world ! :)
$ DONE
```

In ClarkData.java we got permission from Natasha to use `static` for `encrypt` and `decrypt`.

## Part 1
###TODO list:
- [x] Git practice
- [x] Correctly written ClackData
- [x] Correctly written FileClackData
- [x] Correctly written MessageClackData
- [x] Correctly written ClackServer 
- [x] Correctly written ClackClient
- [x] Correctly written and running TestClackData
- [x] Correctly written and running TestClackServer
- [x] Correctly written and running TestClackClient
- [x] Javadoc comments, and Javadoc folder
- [x] Report with all questions answered
- [x] Contributions reports
  
### Report

**Diagram:**

![](screenshot.png)

> In your test classes, what happens if you provide a negative value for a port
number, or a null value for a user? How do you think you can fix these issues?

There is nothing that immediately breaks when inserting these edge case into our classes. 
However, as the project progresses and more things start functioning we would start running into issues.
For example a negative value for a port would crash our program because ports must be between 1 and 65535.
If we asked the operating system for port `-30` it would likely just refuse our request or if 
for some reason it did allocate a port it would be something unexpected.

A bad solution is to "just write good code" and "trust the user". These solutions won't work in a production 
environment. A general solution to all of these types of problems is to check for these edge cases and raise exceptions
whenever they are reached.