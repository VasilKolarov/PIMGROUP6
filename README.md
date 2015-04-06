# How Do I Use Github
- I understand not everyone knows how to use Github / version control but it's a skill worth learning and easy to get 
the hang of
- To learn the basic workflow I **highly recommend you watch this video**
https://www.youtube.com/watch?v=oFYyTZwMyAg

## Some really basic Github Instructions
I'm not going to go into detail about how to use Github, there are millions of youtube videos / online resources out 
there that explain it far better than I ever could. But the below is intended as a very basic getting started guide

###Cloning the Repository
- First you need to clone the repository with 

        git clone https://github.com/Liefdrag/PIMGROUP6.git 
        
        
- For those of you using eclipse / another IDE of choice I highly suggest that you clone this repository and 
then drag those files into your Eclipse workspace. **make sure you move the hidden .git file as this is vital**
  
###Creating a branch
- By default git has a single branch 'master' where code can be added, but having multiple people pushing code to master
can be confusing so I suggest each member makes a new branch for each feature they are working on
 
        git branch <desired name of branch>

        
e.g. if you're working on the notes section of the application you'd make new branch with the following

        git branch notes

- To move to this branch run the following

        git checkout <name of your branch> 
        
in our above 'notes' example you'd run
 
        git checkout notes
        
you can actually create a branch and move to the new branch with the same command with the following

        git checkout -b <name of your branch>
      
More information on branching can be found here http://git-scm.com/book/en/v2/Git-Branching-Basic-Branching-and-Merging 

Nice visual example of how brancing works
![Basic github branching](http://hades.name/media/git/git-history.png)       
---
###Keeping Up To Date With Changes
- At any time you can download any changes made to the branch you are currently on with the following command. You should
make sure you do this when you sit down to add changes to any files on a branch, as the bug you wanted to fix could 
have been already fixed by someone else

        git pull

###Adding Your Own Files
- Once you've coded in your feature you can tell git to track new changes with the following

        git add <name of your file>
        
e.g. If you've created a file with a bunch of tests called UnitTest.java you can run

        git add UnitTest.java
 
  
- Sidenote: when adding files don't be lazy and run 'git add -all or git add *' in your root project directory as this 
will add unnecessary eclipse files like .classpath or /bin folders which can differ depending on OS or IDE of choice, 
only add what is necessary
  
---
###Uploading Your Changes
- You then need to add a commit message to tell other members of the group what changes you made, try to make commit 
messages usefull so other members know whats going on

        git commit -m "<Details of what changes to made here>"

---

- Once you've added files to your local branch you need to push these files to remote upstream repository on github, 
this can be done with the following

        git push origin <name of your branch>

following our above examples

        git push origin notes

--- 
###Merging Your Feature (proceed with caution)
- In order to merge your feature into the master branch (assuming everything works) do the following
    - Move back to your local master branch and pull any changes from the upstream repository. Files could have changed
    whilst you were busy working on your feature
    
        **Proceed with caution if in doubt ask Jack**

        git checkout master
        git pull
        git merge <name of your branch>


# Adding libraries to build path
Some of the features included in the project requires external libraries to function properly, there's no point writing
all the code from scratch when libraries exist already

All the necessary libraries are stored in the /libs folder, but you'll need to manually add them to your build path. I highly
suggest you use an IDE to do this as doing it via command line can be a pain in the arse

- For Eclipse users: http://www.wikihow.com/Add-JARs-to-Project-Build-Paths-in-Eclipse-(Java) 

- For IntelliJ users (because it's x1000 times better than Eclipse) follow either:
    - https://www.jetbrains.com/idea/help/configuring-module-dependencies-and-libraries.html 
    - http://stackoverflow.com/questions/1051640/correct-way-to-add-lib-jar-to-an-intellij-idea-project 

---

# Setting up JDBC and Mysql
All user information from the Personal Information Manager such as contacts, notes and appointements will be stored 
in a simple database. We're going to use JDBC to connect Java to a simple MySql database

##Installing MySql
- Linux (ubuntu people (Vasil + Tommy ? )) I followed these instructions here https://help.ubuntu.com/community/JDBCAndMySQL
- Windows people follow these instructions  http://dev.mysql.com/doc/refman/5.7/en/windows-installation.html 
- OSX people, ask Steve Jobs

##Using MySql##
Although the java code automatically creates the database if it does't already exits you may need to jump into the MySql
shell do debug any errors and check everything is running fine

Comprehensive instructions on using the MySql shell can be found here: http://www.tutorialspoint.com/jdbc/jdbc-sql-syntax.htm 

##Useful MySql Commands 

- From the temrinal/command line you can access the mysql shell/prompt by running
            
            mysql -u root -p

- You should be met by a shell that looks something like the following
            
            mysql > 

- Go ahead and manually create a Database with

            create database PIMDataBase;

- Run the following to check that the database has been created the ';' is
  important

            SHOW DATABASES;

- You should be able to see PIMDataBase in the table

- To remove the Database you can run the following command
            
            DROP DATABASE PIMDataBase;

##Populating the Database
- Exit the mysql shell then run the data base population scripts with

            mysql --user=root --password=root PIMDataBase < PopulateDBWithTestData.sql
