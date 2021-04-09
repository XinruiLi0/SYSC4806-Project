Porject: COVID Contract Tracing Questionnaire

Travis CI Link: https://travis-ci.com/github/XinruiLi0/SYSC4806-Project

Heroku deploy Link: https://sysc-4806-covid-questionnaire.herokuapp.com/

Group Member: 

	Junyuan Chen, Xinrui Li, Xiling Wang, Xinyu Chen, Zewen Chen

Meeting Schedule: 
    
	Every Sunday and Thursday. Distributed the role and responsibilities of each group member on Sunday; Checked and discussed the current progress on Thursday.

Project Description:

	Contact tracing personnel have to frequently call Contact Cases, i.e., people who were in contact with someone known to have COVID, to find out whether they are experiencing symptoms and whether they need any support while they are in quarantine.
 
	We want to relieve their work by providing a questionnaire that the Contact Cases can fill out themselves.

	The Public Health Unit is responsible for creating the case in the system, providing the name and phone number/email of the Contact Case, along with private information (NOT to be made visible to the Contact Case!) such as the Case ID that the Contact Case is related to, and the Exposure Date. The Contact Case will then receive a daily reminder (by phone or email) to fill out a questionnaire, one per day for X days, asking questions such as: "since yesterday's questionnaire, have you remained inside you residence?", "do you need any support to adhere to self-isolation?", (if yes) "what type of support do you need? (food/groceries, medications/prescriptions, pet care), "are you experiencing any symptoms? (cough, fever, runny nose).


Sart up Instructions:

	1. Enter https://sysc-4806-covid-questionnaire.herokuapp.com/ in the web browser

	2. Click "Link to project page"

	3. Start your questionnarie!


User Instructions:

	1. enter your name.

	2. enter your valid email address.

	3. select option based on your own situation (enter support type if you need).

	4. press "Add/Update Questionnaire through VUE.js" button or "Add/Update Questionnaire through AJAX" button to add or update, and your record would be displayed down below the "Search Email" button.

	5. you may also enter email and search the corresponding record, the history record would be displayed below the "Search Email" button.


System Design:

  Front end: 

	View of the project, the html page displayed to the client and passing message back and forth by AJAX or VUE.js
  
  Back end:

  	WebController: Controller of the questionnaire project that inter-connect model and view by spring

  	Questionnaire: Model part for the questionnaire to information contained in questionnaire.

	QuestionnaireRepo: Used as a temporary database to store model instance
  
  Deployment / Database:

	Deploy the project on Heroku using Travis CI to continuously integration


Contributions for m1:

	Xinyu Chen -- Front-End

	Xinrui Li -- Deployment / Database

	Junyuan Chen -- Back-End

	Zewen Chen -- Testing

	Xiling Wang -- Testing and documentation
	
Contributions for m2:

	Xinyu Chen -- Front-End and Back-End

	Xinrui Li -- Database and Back-End

	Junyuan Chen -- Back-End and Front End

	Zewen Chen -- Testing and Front End

	Xiling Wang -- Front-End and Documentations

Contributions for m3:

	Xinyu Chen -- Front-End and Back-End

	Xinrui Li -- Front-End and Back-End

	Junyuan Chen -- Back-End, Front End and Testing

	Zewen Chen -- Testing, Front End, and Back-End

	Xiling Wang -- Front-End, Back-End, and Documentations
	
Deadlines:

	Milestone 1 Demo March 12th 

	Milestone 2 Demo March 26th

	Milestone 3 Demo April 9th

	
Further Improvements:
	
	m1: The general functions and features have been done in milestone 1. In milestone 2, The functions and content will be expanded (the questionnaire will becloser to the real use). Restriction settings will be added, for example: the user must fill in all the options  before submit the questionnaire, the name and email address cannot be repeated with the existing one, the user must fill in the email address and name before they can submit the questionnaire, etc.
	m2: The database, front-end page, and two more features have been done in milestone 2. The more details need to be done in milestone 3. such as: error handling, more friendly user interface. 
	m3: we implemented "send email" feature, onece the user finished the questionnaire. they will receive a confirmation email which contains the questionnaire's information and an email validation before submitting the questionnaire both in vue and controller. we also added few featues by using vue.js. such as vue component, class and style bindings, vue conditionals, list rendering and filters. we also added temporary questionnaire result. and there is a error handling to check if the email's style is valid or not. The background color would vary according to the user's selection, if the user is in danger, the color would become yellow or red to represent his/her current situation. 
	
