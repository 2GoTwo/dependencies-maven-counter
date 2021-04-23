package me.meskine.pluginsmaven.dependeciescounter;

import java.util.List;

import org.apache.maven.model.Dependency;
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

	@Parameter(property = "scope")
	private String scope;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		@SuppressWarnings("unchecked")
		List<Dependency> dependencies = project.getDependencies();
		long dependenciesCount = dependencies.stream().filter((Dependency dep) -> {
			return (scope == null || scope.isEmpty()) || scope.equals(dep.getScope());
		}).count();
		getLog().info(
				String.format("Number of dependencies of scope '%s' ---> %d", scope.toUpperCase(), dependenciesCount));
	}

}
