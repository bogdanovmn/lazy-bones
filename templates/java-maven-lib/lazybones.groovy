Map<String, String> props = [:]

props.projectKey = ask('Выберите projectKey [templateproject]: ', 'xxx')
props.githubName = ask('Выберите GitHub-Project-Id [project-name]: ', 'xxx')
props.pkgPrefix = 'com.github.bogdanovmn'
props.pkgProjectPrefix = props.pkgPrefix + '.' + props.projectKey


static String stringToDirName(String packageName) {
	return packageName.replaceAll(/[.-]/, '/')
}

new File(projectDir as File, "src/main/java/${stringToDirName(props.pkgProjectPrefix)}").mkdirs()
new File(projectDir as File, "src/test/java/${stringToDirName(props.pkgProjectPrefix)}").mkdirs()
new File(projectDir as File, "gitignore").renameTo(
	new File(projectDir as File, ".gitignore")
)

processTemplates '**/*.java', props
processTemplates '**/pom.xml', props
processTemplates '**/*.properties', props
processTemplates '**/*.yml', props
processTemplates '**/*.html', props