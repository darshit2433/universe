API requests

1)   List families in a particular universe

API type:- GET

Api :-  http://localhost:8081/universe/webresources/universe/getFamilies


2)  Check if families with given family_id have the same power in all universes. If powers don’t match then family_id is unbalanced

API type:- GET

Api :-  http://localhost:8081/universe/webresources/universe/checkBalance?id=2

3)   Find unbalanced family_ids

API type:- GET

Api :-  http://localhost:8081/universe/webresources/universe/findUnbalanced


Here  I am assuming that I also have to count the universe without any person of the given family  
so given family will be unbalance 
family 1
universe 1 power:- 2
universe 2 power:- 2
universe 1 power:- no member exist so power = 0 

and family without any member is also balanced as its power is 0 in all the universe 


4)  Balance given family_id 

API type:- GET

Api :-  http://localhost:8081/universe/webresources/universe/findUnbalanced

I will set the power of all person to 0 this will give 2nd chance to all the members of the family to become balanced family



Please Find db dump in the code with  the name  Dump20190706.sql
