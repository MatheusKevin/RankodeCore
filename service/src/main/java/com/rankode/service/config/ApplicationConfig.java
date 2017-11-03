package com.rankode.service.config;

import java.util.HashSet;
import java.util.Set;
import com.rankode.service.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {        
                
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(CORSFilter.class);
                
                classes.add(DeveloperRest.class);
                classes.add(ProjectRest.class);
                classes.add(RepositoryAccountRest.class);
                classes.add(IndicatorRest.class);
                classes.add(HintRest.class);
                classes.add(MetricRest.class);
                classes.add(InfluenceRest.class);
                classes.add(MetricGroupRest.class);
                classes.add(TargetRest.class);
                classes.add(MetricResultRest.class);
                classes.add(CommitRest.class);
                classes.add(CollaboratorRest.class);
                classes.add(NotificationRest.class);
                classes.add(InviteRest.class);
		return classes;
                
                
	}
}
