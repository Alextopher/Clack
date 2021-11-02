# Clack
Clack _(Clarkson Slack)_ project for CS-242.

By Humaira Rezaie and Christopher Mahoney.

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