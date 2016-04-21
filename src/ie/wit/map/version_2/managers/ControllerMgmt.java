package ie.wit.map.version_2.managers;

import ie.wit.map.version_2.views.add_item.AddItemCtrl;
import ie.wit.map.version_2.views.add_photo.AddPhotoCtrl;
import ie.wit.map.version_2.views.modal_root.ModalRootCtrl;
import ie.wit.map.version_2.views.root.RootCtrl;
import ie.wit.map.version_2.views.show_items.ShowItemCtrl;
import ie.wit.map.version_2.views.map.Map;
import ie.wit.map.version_2.views.show_photos.ShowPhotosCtrl;

/**
 * Created by Joe on 20/04/2016.
 */
public class ControllerMgmt
{
	private RootCtrl rootCtrl;
	private Map mapCtrl;
	private ModalRootCtrl modalRootCtrl;
	private AddItemCtrl addItemCtrl;
	private ShowItemCtrl showItemCtrl;
	private AddPhotoCtrl addPhotoCtrl;
	private ShowPhotosCtrl showPhotosCtrl;

	public ControllerMgmt()
	{

	}

	public RootCtrl getRootCtrl()
	{
		return rootCtrl;
	}

	public void setRootCtrl(RootCtrl rootCtrl)
	{
		this.rootCtrl = rootCtrl;
	}

	public Map getMapCtrl()
	{
		return mapCtrl;
	}

	public void setMapCtrl(Map mapCtrl)
	{
		this.mapCtrl = mapCtrl;
	}

	public ModalRootCtrl getModalRootCtrl()
	{
		return modalRootCtrl;
	}

	public void setModalRootCtrl(ModalRootCtrl modalRootCtrl)
	{
		this.modalRootCtrl = modalRootCtrl;
	}

	public AddItemCtrl getAddItemCtrl()
	{
		return addItemCtrl;
	}

	public void setAddItemCtrl(AddItemCtrl addItemCtrl)
	{
		this.addItemCtrl = addItemCtrl;
	}

	public ShowItemCtrl getShowItemCtrl()
	{
		return showItemCtrl;
	}

	public void setShowItemCtrl(ShowItemCtrl showItemCtrl)
	{
		this.showItemCtrl = showItemCtrl;
	}

	public AddPhotoCtrl getAddPhotoCtrl()
	{
		return addPhotoCtrl;
	}

	public void setAddPhotoCtrl(AddPhotoCtrl addPhotoCtrl)
	{
		this.addPhotoCtrl = addPhotoCtrl;
	}

	public ShowPhotosCtrl getShowPhotosCtrl()
	{
		return showPhotosCtrl;
	}

	public void setShowPhotosCtrl(ShowPhotosCtrl showPhotosCtrl)
	{
		this.showPhotosCtrl = showPhotosCtrl;
	}
}
