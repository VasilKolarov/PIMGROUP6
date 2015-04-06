# PIMGROUP6 Personal Information Manager

Please excuse my filthy commit messages

# How Do I Use Github
To learn the basic workflow watch this video
https://www.youtube.com/watch?v=oFYyTZwMyAg

# How to contribute
- For those of you using eclipse (Assumung this is everyone) I highly suggest
that you git clone this repository and then drag that file into your Eclipse 
workspace
- If you want to add a feature, make a seperate branch, don't alter any of the
  code in master
- Write the code required for that new feature
- Then merge back to master


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
            
- In feature updates I'll make it so the populate database function works from within Java itself