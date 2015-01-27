# Friends
This is a project created by Lucas Webb, Sam Noyes, and Michelle Ramiz for CSC420, winter term 2015.

All rights reserved.

The creators are not responsible for any damages caused by this code.


DOCS:

Given that getFriendWithName only takes a name input value, Friends with the same name but different hometowns should not be added.
This decision was made due to the assignment.  It called for a method that returns the friends of friends given just a name.
Thus, it is not feasible to have multiple Friends of the same name.

Despite some earlier confusion, the addFriend list in the Friend class only adds the Friend to the current Friend's list.
It DOES NOT also add the current Friend to the other Friend's list.  Same goes with removing friends. There is a method in driver
that handles the removing and adding, and that method DOES add each to the other's list.