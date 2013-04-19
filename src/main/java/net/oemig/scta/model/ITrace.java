package net.oemig.scta.model;

import java.util.List;

public interface ITrace {
	public String getName();
	public List<ISession>getSessions();
}
