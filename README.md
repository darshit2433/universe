API requests

List families in a particular universe
Api type :- GET
Api :-  http://localhost:8081/universe/webresources/universe/getFamilies


Check if families with given family_id have same power in all universes. If powers don’t match then family_id is unbalanced
Api type :- GET
Api :-  http://localhost:8081/universe/webresources/universe/checkBalance?id=2

Find unbalanced family_ids
Api type :- GET
Api :-  http://localhost:8081/universe/webresources/universe/findUnbalanced


Here  I am assuming that i also have to count universe without any person of given family  
so given family will be un balance 
family 1
universe 1 power :- 2
universe 2 power :- 2
universe 1 power :- no member exist so power = 0 

and family without any member is also balced as its power is 0 in all the universe 

Please Find db dump in the code 
