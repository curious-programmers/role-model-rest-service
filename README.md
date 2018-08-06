# role-model-rest-service
The goal is to achieve as good rest service as possible. This is exercise, to improve skills and present some points of view using rather popular frameworks and solutions in the best way possible. 

** This documentation should grow as solution, go to problem domain to see what we would like to achieve at the end ** 

### Problem description 

In this material we would like to gather best practices and considerations of kind if use [POST or PUT](https://stackoverflow.com/questions/630453/put-vs-post-in-rest). 
Also we would like to collect materials that are condensed yet comprehensive. 


### Problem domain  

Our made up problem will be rest service which will help keep track of spending money on car, and also provides some statistical data like average fuel consumption

Requirements 
* option to add car to fleet (registration number is unique id) 
* option to add information about fueling car
* option to add any other spending on car
* getting avg fuel consumption 
* getting total money spent on car 
* getting amount of money spent on car in given period

### Technical decisions 

#### Security 
We need to provide way to authenticate user and secure endpoints

#### Storage 
We need to somehow store data 

#### Deploy 
Service should be easy deployable in some of popular free cloud app engines 

#### Desing patterns 
Every good solution should be easy to understand for other users. Common way to think about problems are designs patterns that should help understand code and way of thinking.

 

###### Materials : 

[Allegro REST Api guideline](https://github.com/allegro/restapi-guideline)

