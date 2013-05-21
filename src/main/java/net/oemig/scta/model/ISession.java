package net.oemig.scta.model;

import java.util.List;

public interface ISession {
	public String getName();
	public void setName(String aSessionName);
	public List<IRun> getRuns();

}
