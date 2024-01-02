package com.lvrmrc.moneybook.presentation.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lvrmrc.moneybook.data.repository.CategoryRepositoryImpl
import com.lvrmrc.moneybook.domain.model.Category
import com.lvrmrc.moneybook.domain.model.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle, private val categoryRepo: CategoryRepositoryImpl
) : ViewModel() {

    val categoryId: String? = savedStateHandle["categoryId"]

    private var _category by mutableStateOf<Category?>(null)
    private var _label by mutableStateOf("")
    private var _icon by mutableStateOf<ImageVector?>(null)
    private var _color by mutableStateOf<Color?>(null)
    private var _type by mutableStateOf(TransactionType.EXPENSE)

    val category: Category? get() = _category
    val label: String get() = _label
    val icon: ImageVector? get() = _icon
    val color: Color? get() = _color
    val type: TransactionType get() = _type

    private val _hasChanges = derivedStateOf {
        category?.let {
            label != it.label || icon != it.icon || color != it.color || type != it.type
        } ?: true
    }

    private val _areFieldsValid = derivedStateOf {
        label.isNotBlank() && icon !== null && color?.equals(null) == false
    }

    val fabEnabled = derivedStateOf { _areFieldsValid.value && _hasChanges.value }

    fun setLabel(label: String) {
        _label = label
    }

    fun setIcon(icon: ImageVector) {
        _icon = icon
    }

    fun setColor(color: Color) {
        _color = color
    }

    fun setType(type: TransactionType) {
        _type = type
    }

    init {
        initCategory()
    }

    /**
     * Map category stored in savedStateHandle to corresponding fields
     */
    private fun initCategory() {
        categoryId?.let { id ->
            viewModelScope.launch {
                _category = categoryRepo.getById(UUID.fromString(id))

                _category?.let { cat ->
                    val (_, label, icon, color, type, _) = cat
                    _label = label
                    _icon = icon
                    _color = color
                    _type = type
                }
            }
        }
    }

    /**
     * Store new category to database
     */
    fun addCategory() {
        viewModelScope.launch {
            if (_areFieldsValid.value) {
                categoryRepo.insert(
                    Category(
                        id = _category?.id ?: UUID.randomUUID(),
                        label = label,
                        icon = icon!!,
                        color = color!!,
                        type = type,
                        lightText = true
                    ).toEntity()
                )
            }
        }
    }


    /**
     * Deletes a category from database
     */
    fun deleteCategory() {
        categoryId?.let {
            viewModelScope.launch {
                categoryRepo.deleteById(
                    UUID.fromString(it)
                )
            }
        }
    }
}
