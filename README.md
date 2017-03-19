# recipe-app

Little project, service for recipes

###GET
####/recipe
get all recipes
####/recipe?ingredients=list,of,ingredients
get all recipes with the ingredients
####/recipe/id/{id}
get recipe by id

###POST
####/recipe
Save a recipe
   
       {
         "description": "string",
         "image": "string",
         "ingredients": [
           {
             "name": "string",
             "portion": 0,
             "portionUnit": "string"
           }
         ],
         "name": "string",
         "preparation": {
           "readyIn": 0,
           "steps": [
             {
               "description": "string",
               "number": 0
             }
           ],
           "timeCooking": 0,
           "timePreparation": 0
         }
       }
  
 
