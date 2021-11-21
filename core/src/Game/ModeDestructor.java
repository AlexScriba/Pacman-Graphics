package Game;

import java.util.ArrayList;

public class ModeDestructor {
private final ArrayList<Mode> modes=new ArrayList<Mode>();
private static ModeDestructor instance=new ModeDestructor();


public static ModeDestructor getInstance() {
	return instance;
}


public void destroyAllModes() {
	for(Mode m: modes) {
		m.deleteMode();
	}
	modes.clear();
}

/*
public void destroyMode() {
	modes.clear();
}
*/

public void addMode(Mode m) {
	modes.add(m);
}



}
