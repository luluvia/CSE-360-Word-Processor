# CSE 360 Word Processor

### What is this repository for? ###

* CSE 360 Fall 2019 project for creating a word processor application. Project group 40. Wednesday class.

### How do I get set up? ###

Clone the repository to your local environment either using the Git command-line interface or a Git GUI client (GitKraken, GitHub Desktop, SourceTree, magit etc).
Set your local credentials so that, when you commit changes, you can be recognized.

You will need:

* [OpenJDK 13](https://jdk.java.net/13/)
* [Apache Maven 3.6.2 or newer](https://maven.apache.org/download.cgi)
* [JavaFX 13.0.1](https://gluonhq.com/products/javafx/)

Install the three development kits above. OpenJDK needs to go in your JAVA folder, while the other two can go in a separate folder. You'll need to set your JAVA_HOME, MAVEN_HOME, and Path variables for all three. See the installation pages for each to learn how to do this.
In your preferred IDE, import pom.xml in the project as a Maven project. It should automatically set up your project. If further modification is needed, consult your IDE's Maven setup page.
JavaFX [SceneBuilder](https://gluonhq.com/products/scene-builder/thanks/?dl=/download/scene-builder-11-windows-x64/) is recommended to work with the GUI. SceneBuilder 11.0.0 or newer should work for JavaFX 13.

### Project management ###

We will create Kanban project boards using GitHub's issue tracker and project manager. That way, we can set up tasks, assign our group members to individual tasks, and monitor progress and make to-do lists. If you have an idea, submit it as an "Issue" so that we can track it as a potential development. You can find the projects [here](https://github.com/luluyume/CSE-360-Word-Processor/projects).

Kanban boards make life easier by allowing us to implicitly communicate through assigning members to tasks and marking them resolved when done.

### Contribution guidelines ###

* **We should aim to stick to the [Angular convention for commit messages.](https://github.com/angular/angular/blob/22b96b9/CONTRIBUTING.md#-commit-message-guidelines)**
* Commits (changes to the code saved to the repository) should be specific. Try to make each commit about a specific thing (like one commit for a feature implementation, one commit for styling, one commit for minor fixes, etc).
* For code: use the style template from Canvas for the class. This includes Javadoc. You don't have to go overboard though, and don't add comments for everything, or for redundant messages.
* For anything other than maintenance changes (like version number or comments), make a new branch and make your changes there ***locally* first**. See [Atlassian's tutorial](https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow) on branch workflows.
* Once your feature/fix branch is tested and ready to be merged, make a **pull request** for the repository, with a summary and explanation of what issue/task it resolves and other details. It is recommended to wait for someone else to review before approving the merge, but you can merge directly if you feel the changes are solid.
* For minor changes, like code styling, documentation, and organization, you can commit directly if you feel the changes are solid.
* If in doubt, chat with the others on Discord.

### Who do I talk to? ###

* Use the Discord or Canvas messaging.
* For repository administration questions, message Luis Luvia on the Discord.
