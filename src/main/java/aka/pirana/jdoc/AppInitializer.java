/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aka.pirana.jdoc;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

/**
 *
 * @author aka
 */
public class AppInitializer {
    @Inject
	AppInitializer(PersistService persistenceService) {
		// wake up JPA
		persistenceService.start();
	}
}
