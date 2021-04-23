package me.meskine.pluginsmaven.dependeciescounter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "DependenciesCounter", defaultPhase = LifecyclePhase.COMPILE)
public class DependenciesCounter extends AbstractMojo {

	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	MavenProject project;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		long dependencies = project.getDependencies().stream().count();
		getLog().info("Number of dependencies : " + dependencies);
	}

}
