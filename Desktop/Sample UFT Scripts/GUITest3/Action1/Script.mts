 '--------------------------------------------------------------------------------------
  Dim setBrowser = "iexplore.exe"
  Dim setURL = "https://www.reddit.com" 
  
 '--------------------------------------------------------------------------------------
  'Launch Reddit Application
  Systemutil.Run setBrowser, setURL 
 
  Browser("reddit: the front page").Page("reddit: the front page").Sync
	
  'verification: Verifying whether the reddit application is launched successfully
  If  Browser("reddit: the front page").Page("reddit: the front page").Link("reddit.com").Exist Then
 	  Reporter.ReportEvent micPass,"Reddit Website","Application is Launched Successfully"
  Else
	  Reporter.ReportEvent micFail,"Reddit Website","Application Launch Failed"
  End If
  
  '--------------------------------------------------------------------------------------		 
