package net.oemig.scta.model.exporter;

import net.oemig.scta.model.ITraceModel;

/**
 * Exporter interface. An exporter is used to dump parts of or
 * an entire model in a non-XML way (csv, Excel) as opposed to 
 * use data binding.
 * 
 * @author chris
 */
public interface IExporter {
	public void export(ITraceModel aModel);
}
