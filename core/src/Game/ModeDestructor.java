package Game;

import java.util.ArrayList;

public class ModeDestructor {
private final ArrayList<Mode> modes=new ArrayList<Mode>();
private static ModeDestructor instance=new ModeDestructor();


public static ModeDestructor getInstance() {
	return instance;
}

//void
public boolean destroyAllModes() {
	for(Mode m: modes) {
		m.destroyGA();
		m.destroySearch();
		m.deleteMode();
	}
	modes.clear();
	return true;
}

//void
public boolean addMode(Mode m) {
	modes.add(m);
	return true;
}



}
