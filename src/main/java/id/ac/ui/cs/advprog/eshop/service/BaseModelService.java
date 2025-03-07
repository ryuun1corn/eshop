package id.ac.ui.cs.advprog.eshop.service;

import operations.Creatable;
import operations.Deletable;
import operations.Editable;
import operations.Findable;

public interface BaseModelService<T> extends Creatable<T>, Editable<T>, Deletable, Findable<T> {}
