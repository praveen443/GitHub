Feature: Check addition in Google calculator content 

 Scenario: Addition 
 	Given launch the google homepage 
 	When enter the value "2+2" in textbox
	Then should get correct result "4" 
