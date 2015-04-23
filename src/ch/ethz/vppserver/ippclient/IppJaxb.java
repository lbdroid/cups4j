package ch.ethz.vppserver.ippclient;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.ethz.vppserver.schema.ippclient.AttributeGroup;
import ch.ethz.vppserver.schema.ippclient.AttributeList;
import ch.ethz.vppserver.schema.ippclient.Tag;
import ch.ethz.vppserver.schema.ippclient.TagList;

/**
 * Copyright (C) 2008 ITS of ETH Zurich, Switzerland, Sarah Windler Burri
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.
 * 
 * See the GNU Lesser General Public License for more details. You should have
 * received a copy of the GNU Lesser General Public License along with this
 * program; if not, see <http://www.gnu.org/licenses/>.
 */
public class IppJaxb {
  private final static String TAG_LIST_FILENAME = "config/ippclient/ipp-list-of-tag.xml";
  private final static String ATTRIBUTE_LIST_FILENAME = "config/ippclient/ipp-list-of-attributes.xml";
  private final static String CONTEXT = "ch.ethz.vppserver.schema.ippclient";

  private List<Tag> _tagList = null;
  private List<AttributeGroup> _attributeGroupList = null;

  IppJaxb() throws FileNotFoundException, JAXBException {
    InputStream tagListStream = IppJaxb.class.getClassLoader().getResourceAsStream(TAG_LIST_FILENAME);
    InputStream attListStream = IppJaxb.class.getClassLoader().getResourceAsStream(ATTRIBUTE_LIST_FILENAME);

    TagList tagList = (TagList) unmarshal(tagListStream, CONTEXT);
    _tagList = tagList.getTag();

    AttributeList attributeList = (AttributeList) unmarshal(attListStream, CONTEXT);
    _attributeGroupList = attributeList.getAttributeGroup();
  }

  public void marshal(Object root, String filename) throws JAXBException, FileNotFoundException {
    if (root == null) {
      System.err.println("IppJaxb.marshal(): root is null");
      return;
    }
    if (filename == null) {
      System.err.println("IppJaxb.marshal(): filename is null");
      return;
    }

    String packName = getClass().getName();
    if (packName.indexOf('.') != -1) {
      packName = packName.substring(0, packName.lastIndexOf('.'));
    }

    JAXBContext jc = JAXBContext.newInstance(packName);
    Marshaller m = jc.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    m.marshal(root, new FileOutputStream(filename));
  }

  /**
   * 
   * @param inputStream
   * @param context
   * @return
   * @throws JAXBException
   * @throws FileNotFoundException
   */
  public Object unmarshal(InputStream inputStream, String context) throws JAXBException, FileNotFoundException {
    if (inputStream == null) {
      System.err.println("IppJaxb.unmarshal(): inputStream is null");
      return null;
    }
    if (context == null) {
      context = getClass().getName();
      context = context.substring(0, context.lastIndexOf('.'));
    }

    JAXBContext jc = JAXBContext.newInstance(context);
    Unmarshaller u = jc.createUnmarshaller();
    return u.unmarshal(inputStream);
  }

  /**
   * 
   * @param inputStream
   * @return
   * @throws FileNotFoundException
   * @throws JAXBException
   */
  public Object unmarshal(InputStream inputStream) throws FileNotFoundException, JAXBException {
    return unmarshal(inputStream, null);
  }

  /**
   * 
   * @return
   */
  public List<Tag> getTagList() {

    return _tagList;
  }

  /**
   * 
   * @return
   */
  public List<AttributeGroup> getAttributeGroupList() {
    return _attributeGroupList;
  }
}
