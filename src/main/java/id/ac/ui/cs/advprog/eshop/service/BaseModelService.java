package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.service.operations.Creatable;
import id.ac.ui.cs.advprog.eshop.service.operations.Deletable;
import id.ac.ui.cs.advprog.eshop.service.operations.Editable;
import id.ac.ui.cs.advprog.eshop.service.operations.Findable;

public interface BaseModelService<T> extends Creatable<T>, Editable<T>, Deletable, Findable<T> {}
