PLEASE READ:

I have edited this project a bit from my original design as I've worked on it more these past few
weeks. Originally, I intended for the application to have an admin user capable of modifying
the clothing items a user could be suggested. However, after completing the suggestOutfit
method in Main.java I felt that the addition of an admin user did not add to the actual
functionality of the application. I have kept the admin user files and related code intact and simply
commented out some of the related code in the Main.java file in case I decide to revisit this project
and expand on the admin user side of things later.

To run the application, one must run the Main.java. The login screen will them appear.
You are then prompted to login to an existing account or create new account. When creating
an account, you are required to input a password, username, and location. Ideally, the location
would be where you would like to receive weather information from. However, the API I chose to use only 
allows me to pull weather data from one location so this application will only pull weather data from 
Charlotte, NC USA (my chosen location) regardless of what location you type in (for now).

After creating an account, you are redirected back to the login page where you can login with your new
credentials. After logging in, you are brought to the "Weather Information" window, detailing the current
forcast for the location (Charlotte, NC). One thing to note is that the temperature is in degrees Celcius.
Pressing "Okay" on this window will bring you to the User Window, where you will be welcomed. Please then click
the button labeled "What should you wear?". Based on the weather information in the previous screen, you will 
be suggested an outfit to wear. Ta-Da

**If you see "Failed to fetch weather data. Response code: 429" in the terminal it simply means that the free API has
been called to many times in the last hour and you'll have to wait to call the weather data again.