package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.service.operations.Createable;
import id.ac.ui.cs.advprog.eshop.service.operations.Deleteable;
import id.ac.ui.cs.advprog.eshop.service.operations.Editable;
import id.ac.ui.cs.advprog.eshop.service.operations.Findable;

public interface BaseModelService<T> extends Createable<T>, Editable<T>, Deleteable, Findable<T> {}
