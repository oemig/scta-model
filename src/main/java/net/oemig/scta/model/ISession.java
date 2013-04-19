package net.oemig.scta.model;

import java.util.List;

public interface ISession {
	public String getName();
	public List<IRun> getRuns();

}
