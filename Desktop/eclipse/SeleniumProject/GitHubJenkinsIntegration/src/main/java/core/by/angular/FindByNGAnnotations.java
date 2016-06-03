package core.by.angular;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.support.pagefactory.Annotations;


public class FindByNGAnnotations extends Annotations {
	
	private Field field;
	
	public FindByNGAnnotations(Field field) {
		super(field);
		this.field = field;
	}
	  protected ByNG buildByFromLongFindBy(FindByNG findBy) {
		  	HowNG howNG = findBy.howNG();
		    String using = findBy.using();

		    switch (howNG) {
		        
		      case NGREPEAT:
		      	return ByNG.ngRepeater(using);
		      	
		      case NGMODEL:
			      	return ByNG.ngModel(using);
			      	
		      case NGBUTTONTEXT:
			      	return ByNG.ngButtonText(using);
		      case NGCONTROLLER:
		    	  return ByNG.ngController(using);
		      default:
		        // Note that this shouldn't happen (eg, the above matches all
		        // possible values for the How enum)
		        throw new IllegalArgumentException("Cannot determine how to locate element " + field);
		    }
		  }

		  protected ByNG buildByFromShortFindBy(FindByNG findBy) {
		    
		    if (!"".equals(findBy.ngRepeater()))
			      return ByNG.ngRepeater(findBy.ngRepeater());
		    if (!"".equals(findBy.ngModel()))
			      return ByNG.ngModel(findBy.ngModel());
		    if (!"".equals(findBy.ngButtonText()))
			      return ByNG.ngButtonText(findBy.ngButtonText());
		    if (!"".equals(findBy.ngController()))
			      return ByNG.ngButtonText(findBy.ngController());
		    // Fall through
		    return null;
		  }


		  private void assertValidFindBy(FindByNG findBy) {
		    if (findBy.howNG() != null) {
		      if (findBy.using() == null) {
		        throw new IllegalArgumentException(
		            "If you set the 'how' property, you must also set 'using'");
		      }
		    }

		    Set<String> finders = new HashSet<String>();		    
		    if (!"".equals(findBy.ngRepeater())) finders.add("ngRepeat: " + findBy.ngRepeater());
		    if (!"".equals(findBy.ngModel())) finders.add("ngModel: " + findBy.ngModel());
		    if (!"".equals(findBy.ngButtonText())) finders.add("ngButton: " + findBy.ngButtonText());
		    // A zero count is okay: it means to look by name or id.
		    if (finders.size() > 1) {
		      throw new IllegalArgumentException(
		          String.format("You must specify at most one location strategy. Number found: %d (%s)",
		              finders.size(), finders.toString()));
		    }
		  }	
}





