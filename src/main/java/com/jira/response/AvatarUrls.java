package com.jira.response;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@XmlRootElement
public class AvatarUrls {
	private String _48X48;
	private String _24X24;
	private String _16X16;
	private String _32X32;
	public String get48x48() {
		return _48X48;
	}
	public void set48x48(String _48x48) {
		_48X48 = _48x48;
	}
	public String get24x24() {
		return _24X24;
	}
	public void set24x24(String _24x24) {
		_24X24 = _24x24;
	}
	public String get16x16() {
		return _16X16;
	}
	public void set16x16(String _16x16) {
		_16X16 = _16x16;
	}
	public String get32x32() {
		return _32X32;
	}
	public void set32x32(String _32x32) {
		_32X32 = _32x32;
	}
	public String get_48x48() {
		return _48X48;
	}
	public String get_24x24() {
		return _24X24;
	}
	public String get_16x16() {
		return _16X16;
	}
	public String get_32x32() {
		return _32X32;
	}
}
