# Text Editor
This is a simple text build on java.

## Dependencies: 

- [Gradle](https://gradle.org/) as a building tool
- [Maven](https://maven.apache.org/) for tracking an locking depencies

## How to start the project: 

- eclipse: 

     https://www.openmuc.org/hrf_faq/gradle_getting_started/
     http://www.vogella.com/tutorials/EclipseGradle/article.html

- intelliJ: https://www.youtube.com/watch?v=JwPYjnhah3g

## All tasks runnable from root project:

### Application tasks
- `run` - Runs this project as a JVM application

### Build tasks
- `assemble` - Assembles the outputs of this project.
- `build` - Assembles and tests this project.
- `buildDependents` - Assembles and tests this project and all projects that depend on it.
- `buildNeeded` - Assembles and tests this project and all projects it depends on.
- `classes` - Assembles main classes.
- `clean` - Deletes the build directory.
- `jar` - Assembles a jar archive containing the main classes.
testClasses - Assembles test classes.

### Build Setup tasks

- `init` - Initializes a new Gradle build.
- `wrapper` - Generates Gradle wrapper files.

### Distribution tasks

- `assembleDist` - Assembles the main distributions
- `distTar` - Bundles the project as a distribution.
- `distZip` - Bundles the project as a distribution.
- `installDist` - Installs the project as a distribution as-is.

### Documentation tasks
- `groovydoc` - Generates Groovydoc API documentation for the main source code.
- `javadoc` - Generates Javadoc API documentation for the main source code.

### Help tasks
- `buildEnvironment` - Displays all buildscript dependencies declared in root project 'text-editor'.
- `components` - Displays the components produced by root project 'text-editor'. [incubating]
- `dependencies` - Displays all dependencies declared in root project 'text-editor'.
- `dependencyInsight` - Displays the insight into a specific dependency in root project 'text-editor'.
- `dependentComponents` - Displays the dependent components of components in root project 'text-editor'. [incubating]
- `help` - Displays a help message.
- `model` - Displays the configuration model of root project 'text-editor'. [incubating]
- `projects` - Displays the sub-projects of root project 'text-editor'.
- `properties` - Displays the properties of root project 'text-editor'.
- `tasks` - Displays the tasks runnable from root project 'text-editor'.

### Verification tasks
- `check` - Runs all checks.
- `test` - Runs the unit tests.

### Rules
Pattern: clean<TaskName>: Cleans the output files of a task.
Pattern: build<ConfigurationName>: Assembles the artifacts of a configuration.
Pattern: upload<ConfigurationName>: Assembles and uploads the artifacts belonging to a configuration.