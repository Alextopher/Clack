### Feedback
> **FIXED** (-0.05) In ClackData, the default type for the default constructor should be one of the four type constants.

This feels like a really hard default to set in good conscious. 
Personally I would not even have a default constructor
for this class because it's not going to provide any value. 
It is only going to cause confusion.

> **FIXED** (-0.05) In ClackData, the whole class should be declared as abstract.
>
> **FIXED** (-0.1) In ClackData, getData() is missing.
>
> **FIXED** (-0.1) In FileClackData, getData() is missing.
>
> **FIXED** (-0.1) In MessageClackData, getData() is missing.

> **FIXED** (-0.05) In ClackClient, equals() should take dataToSendToServer and dataToReceiveFromServer into account.
>
> **FIXED** (-0.05) In ClackClient, hashCode() should take dataToSendToServer and dataToReceiveFromServer into account.
>
> **FIXED** (-0.05) In ClackServer, hashCode() should take closeConnection, dataToSendToClient, and dataToReceiveFromClient into account.
>
> **FIXED** (-0.05) In ClackServer, equals() should take closeConnection, dataToSendToClient, and dataToReceiveFromClient into account.

Part of my issue is just that implementing `Equals` this way on these classes does not make sense. 
If I need to know if 2 servers are the same object I probably just want to know they have the same pointer.
I can't imagine a scenario where I would have two different servers with the same port, connection status, client name, etc.
This feels vacuously true, this requirement only works because we make a bad assumption.

> (-0.1) In TestClackClient, should test every method.
>
> (-0.1) In TestClackData, should test every method.
>
> (-0.1) In TestClackServer, should test every method.
>
> (-0.1) The class diagram is not fully correct.
