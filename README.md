# How Do I Use Github
To learn the basic workflow watch this video
https://www.youtube.com/watch?v=oFYyTZwMyAg

# How to contribute
- For those of you using eclipse (Assumung this is everyone) I highly suggest that you git clone this repository and 
then drag those files into your Eclipse workspace
- When pushing code to github don't be lazy and run 'git add -all or git add *' as this will add unncessary eclipse 
files like .classpath or /bin folders which can differ depending on OS or IDE of choice, only add what is necessary
- If you want to add a feature, make a separate branch, try not to alter any code in master
- Write the code required for that new feature
- Then merge back to master


--- 

# Adding libraries to build path
Some of the features included in the project requires external libraries to function properly, there's no point writing
all the code from scratch when libraries exist already

All the necessary libraries are stored in the /libs folder however you'll need to manually add to your build path. I highly
suggest you use an IDE to do this as doing it via command line can be a pain in the arse

- For Eclipse users: http://www.wikihow.com/Add-JARs-to-Project-Build-Paths-in-Eclipse-(Java) 

- For IntelliJ users (because it's x1000 times better than Eclipse) follow either:
    - https://www.jetbrains.com/idea/help/configuring-module-dependencies-and-libraries.html 
    - http://stackoverflow.com/questions/1051640/correct-way-to-add-lib-jar-to-an-intellij-idea-project 

---

# Setting up mysql and the database

##Installation msql
- first you need to make sure you have mysql installed
    - Linux (ubuntu people (Vasil + Tommy ? )) I followed these instructions here https://help.ubuntu.com/community/JDBCAndMySQL
    - Windows/Mac people I have no clue sorry


~~##Creating the Database~~ 

No longer necessary since ConnectToDataBase.java will create once
automatically, use instructions below as a fail safe if this doesn't work

- From the temrinal/command line access the mysql shell/prompt by running
            
            mysql -u root -p

- You should be met by a shell that looks something like the following
            
            mysql > 

- Go ahead and manually create a Database with

            create database PIMDataBase;

- Run the following to check that the database has been created the ';' is
  important

            SHOW DATABASES;

- You should be able to see PIMDataBase in the table

##Populating the Database
- Exit the mysql shell then run the data base population scripts with

            mysql --user=root --password=root PIMDataBase < PopulateDBWithTestData.sql
