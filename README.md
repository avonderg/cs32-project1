# README

#Section 2: Overall Description
#2.1 User Needs
We are creating a recommender system that generates compatible matches between students. This system will be used within the university, by each class, whenever students need to be paired amongst themselves for either group projects, homework assignments, and in-class activities. For this product, the second users would be the professors and teaching assistants. However, their needs are ultimately the same as the user– having students be matched with the most compatible individuals would allow for greater engagement and learning. The survey asks the following questions:
- Demographics: age, gender identity, race, nationality, graduation year
- Personal information: ID, email, social security number, interests
- Logistics: weekly availability, meeting style, meeting availability
- Technical background: years of experience, communication style, software engineering confidence, strengths, weaknesses, skills

Based on user research, we’ve found conflicting user needs amongst stakeholders. Amongst the 4 students that were interviewed, each of them expressed different opinions on which attributes they would prefer the recommender system used to generate matches, those of which included:
- Their cultural or ethnic background
- Technical skills
- Logistical conditions

While some students expressed that they wanted to be paired with others that were similar to them, others stated that they preferred the recommender system to pair them to the most different possible students. There was also a notable difference in opinion regarding the system using student’s personal data (regarding their background). Although one commented that they’d feel more comfortable amongst others with a similar background, others felt that this data was meant to be kept private.

Users have frequently expressed frustration, as well as discomfort, of not being matched in the most appropriate manner to other students. For this reason, users have expressed an interest in being paired with other students that they are either similar to, or different from. At the bottom line, this interest is rooted in a desire to have the best possible student partner pairings as possible, in order for the user to feel comfortable, and academically engaged. Thus, in regards to the system, it is important that it generates the most compatible matches amongst students, in the best and most fair way possible. This would make the system stand out, as most recommender systems for group projects used at Brown do not take all of these attributes into account when pairing students.

#2.2 Assumptions and dependencies
**Technical Dependencies**
- Github
- Intellij
- Personal computers for every developer

**Non-technical Dependencies**
- Assumption of no legal conflicts regarding collecting data from users.
- Users are studying the same subjects/classes, and thus can suitably be paired together for projects.

**Normative Assumptions**
- Our recommender is more thorough than current recommender systems used by Brown.
- Pairing students through our superior recommender will improve their academic experience. 

**Financial Dependencies**
- Funds for replacing or repairing personal computers, if needed.

#Section 3: System Features and Requirements
#3.1 Risks
**Stakeholder risks:**

- **Unfair outcomes:** If the recommender system were to match people of the same race or nationalities, it would have the potential to reinforce biases amongst the student body. This would place users of these groups at a disadvantage, increasing the disparities between racial and ethnic groups.
- **Feature representation**: This app’s data does not collect any data on the user’s socioeconomic backgrounds. Therefore, this feature is not represented, or utilized, within the recommender system. However, this is likely for the best, as this would prevent students at an economic disadvantage from being consistently paired amongst themselves (for example, individuals that do not have access devices such as personal computers).
- **Data manipulation:** Once they submit the survey, stakeholders are unable to modify their response. The recommender system works by utilizing these submissions, and then creating matches based off of student responses. Therefore, users input data relevant to the questions presented in the survey, and once they submit the survey, they no longer have access to the data they submitted.
- **Disproportionate benefits:** The system does not benefit one group more than others. This is because the recommender system utilizes the attributes of strengths/weaknesses, as well as skills to lack of skills. Essentially, each group of students matched to each other would contain a broad array of users, balancing all strengths and skills evenly amongst the group. This ensures that each group of stakeholders is benefited equally, and that there are no biases.
- **Publicly accessible data:** No data is publicly accessible– users can only access the recommendations given to them by the system, after it is finished generating compatible matches.
- **Non-consensual data harvesting:** Data is not ever harvested or used without stakeholder consent. Data is only collected after the user submits the survey. After that, it is stored and used by the system to generate match pairings.
- **Blackbox algorithms:** The stakeholders are unable to view the exact recommender process that goes behind creating a match. However, they will be able to infer what attributes that they submitted in their survey were used to make that decision.
- **Community wellbeing:** This program does not disrupt community wellbeing in any way, given that it only functions as a resource to connect compatible users to each other, so that they may feel as comfortable and academically engaged as possible when working together as a group.
- **Inefficient use of resources:** The program does not make any unnecessary uses of resources and/or energy– it only utilizes what is necessary.

**External risks:**

- **Social context:** This program works against systems of oppression – such as racism, sexism, and classicism– by only utilizing technical attributes when matching users. In ensuring that each group has a broad range of technical skills, strengths, and weaknesses, this prevents any certain stakeholder groups from being placed at a disadvantage.
- **Environmental harms:** Lastly, this program does not rely on any services that participate in labor exploitation or create environmental harms

#3.2 Data Requirements

All data below, unless otherwise specified, is collected only with the consent of the user after they have filled out the survey.

- Unique ID (internal)
  - Allows recommender system to distinguish between different users 
  - Therefore, this data will be both stored and used
- Name
  - Allows for users to see who they have been matched with
  - This data will be stored, and only used at the end after matches have been made so that students are aware of who they have been paired with
- Email
  - This data will be stored, and only used at the end after matches have been made so that students are aware of who they have been paired with
- Gender (Private)
  - This data will be stored, but not used, as it will not be utilized by the recommender to generate matches.
- Class year (Private)
  - This data will be stored, but not used, as it will not be utilized by the recommender to generate matches.
- SSN (Private)
  - This data will be stored, but not used, as it will not be utilized by the recommender to generate matches.
- Nationality (Private)
  - This data will be stored, but not used, as it will not be utilized by the recommender to generate matches.
- Race (Private)
  - This data will be stored, but not used, as it will not be utilized by the recommender to generate matches.
- Years of Experience
  - Used to match users with varying years of experience
  - This data will be both stored and used by the system
- Communication style (Private)
  - This data will be stored, but not used, as it will not be utilized by the recommender to generate matches.
- Weekly available hours (Private)
  - This data will be stored, but not used, as it will not be utilized by the recommender to generate matches.
- Meeting style (Private)
  - This data will be stored, but not used, as it will not be utilized by the recommender to generate matches.
- Meeting time (Private)
  - This data will be stored, but not used, as it will not be utilized by the recommender to generate matches.
- Software Engineering confidence
  - Used to match users with varying levels of confidence
  - This data will be both stored and used by the system
- Strengths
  - Used to match users with lack of a certain strength to those who do possess that strength
  - This data will be both stored and used by the system
- Weaknesses
  - Used to match users who have a weakness in one category, to those who possess a strength in that one
  - This data will be both stored and used by the system
- Skills
  - Used to match users with lack of a certain skills to those who do possess that skill
  - This data will be both stored and used by the system
- Interests (Private)
  - This data will be stored, but not used, as it will not be utilized by the recommender to generate matches.

#3.3 System Features

**CSV Reader and REPL**
- We will use Sprint 0, the onboarding project, for the Naive REPL and CSV Reader.
- The REPL will be in the Main class, in a function called run.
- The csv reader will also be in a different class named Reader.
- The data that is read from the csv file will be stored in another class that will likely implement some sort of data structure interface.
- To run the repl, enter the command “./run” in terminal.
- To run a search/use the recommender, the user will enter relevant information in the format “attribute:type.”

**Bloom Filter**
- The bloom filter will be implemented in its own class named Bloom.
- The bloom filter will be initiated when an input is passed into the REPL.

**KD Tree**
- The KD tree will be implemented in its own class named KDTree.
- The KD Tree will be initiated when an input is passed into the REPL.
#3.4 Functional Requirements

**CSV Reader/REPL**
- This is how the user will access the recommender. They will pass input text and the file name of the data into the REPL, so that the program can parse and store the data.
- Thus, the user/student will be able to choose which file to use to find their recommendation.
- This part of the recommender collects user input directly, then calls the bloom filter and KD tree to produce a recommendation.

**Bloom Filter**
- The bloom filter is used when the user is searching for non-numerical attributes, such as strengths, weaknesses, skills, race, nationality, skills, interests, etc.
- The bloom filter searches through a data structure that stores all the data.

**KD Tree**
- The KD tree is used when the user is searching for numerical attributes, such as class year, years of experience, weekly available hours, meeting time, and software engineering confidence.
- The KD tree searches through a data structure that stores all the data.

#3.5 Testing Plan
We have written a range of system tests that cover typical usage and atypical edge cases, as summarized below. You can view each system test here.

We have also written a range of unit tests for each non-void method in our algorithms.

**How to run the tests from the Command line:**
- To run all unit tests, run “mvn test” (exclusively runs the test) or “mvn package” (which builds the project and runs the tests)
  - To generate an HTML report of the unit test results stored at target/site/surefire-report.html run “mvn surefire-report:report”
- To run a system tests, run ./cs32-test <test> where <test> is the file path to a system test. To run all given system tests together, run “./cs32-test tests/sprint-1/given/*/*.test”
  - To run just an individual test, replace the *.test with the name of the individual system tests
  - To run, enter the command "./run" in terminal.
  - To run the given system tests for kdTree and bloomFilters, run
    -  ./cs32-test tests/sprint-1/given/kdTree/*.test
    -  ./cs32-test tests/sprint-1/given/bloomFilter/*.test
  - To run system tests we created for kdTree and bloomFilters run:
    - ./cs32-test tests/sprint-1/created/kdTree/*.test
    - ./cs32-test tests/sprint-1/created/bloom/*.test

**- Testing Plan**

[Unit Tests]: REPL
- Tests that information from each line of the CSV is being parsed and stored correctly in the Student class.

[Unit Tests]: k-D Tree
- Tests that a k-D Tree can be successfully instantiated and that the createKDTree() method successfully executes its recursion.
- Tests that the k-D tree k-nearest neighbors algorithm works correctly, outputting the correct order of student IDs.
- Edge cases such as when k is 0 are also tested.
- Tests that the compare() method of the SortByCoord class that implements the Comparator interface successfully compares two coordinates of the given dimension of the two nodes passed in.

[Unit Tests]: Bloom Filter
- Tests that each of the getter methods work as they should
- Tests that the two calculation methods for finding k, the number of hash functions, and m, the size of the bitset, are successfully calculating those respective values
- Tests that tokenized command line input is being read correctly by the BloomFilter class, and that each respective method (create_bf, insert_bf, and query_bf) is being called
- Tests that bloom filters are being instantiated and generated by the user correctly, as well as inserting and querying is working as it should

[System Tests]: k-D Tree
- Tests that a k-d tree can be successfully instantiated using the “load_kd” command
- Tests that a k-d tree can successfully execute its k-nearest neighbors algorithm using the “similar-kd” REPL command, including edge cases such as invalid user ID’s, k greater than the number of students loaded, negative and 0 k’s, and more.

[System Tests]: Bloom Filter
- Tests bloom filters with false positives, false negatives, and true positives to make sure that the BF functions as expected


#3.6 External Interface Requirements

Our app doesn’t have an external user interface, but students do interact with it via the cs0320 course survey thety are given.

- How will your user interact with your app? Specifically, how will you make it accessible to visually, motor, cognitive, or otherwise impaired users?
  - Users will interact with the app by providing their information to data sources, but they won’t be running anything directly. Users will not be directly interacting with any application interface, so we don’t have any user interface to make accessible. Our program will load data from external CSVs that have student data obtained from a survey given to cs0320 students. The only way for the students to interact with the program is to answer the survey.
- If applicable, how will your app communicate with external software?
  - We’ll likely host our database of students eventually on Google Cloud Software or Firebase

#3.7 Non-functional Requirements

- What standards of performance must your product meet?
  - The K-D Tree algorithm should be able to find k nearest neighbors in O(n log n) time
  - The Bloom Filter
- What standards of security must your product meet?
  - The product must be able to securely protect private user data.
- What standards of privacy must your product meet?
  - Our app deals with private user data, so we have a duty to our users to keep their data safe and secure and avoid exposing their personal information.
- How “flashy” and aesthetically pleasing does your UI need to be? How accessible does your UI need to be?
  - At this stage of the software, there is no UI. 


