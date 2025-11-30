package pnm.tigad.addnavigation.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import pnm.tigad.addnavigation.model.MenuItem
import pnm.tigad.addnavigation.model.OrderUiState
import pnm.tigad.addnavigation.model.MenuItem.EntreeItem
import pnm.tigad.addnavigation.model.MenuItem.AccompanimentItem
import pnm.tigad.addnavigation.model.MenuItem.SideDishItem
import java.text.NumberFormat


class OrderViewModel : ViewModel() {

    private val taxRate = 0.08

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun updateEntree(entree: EntreeItem) {
        val previousEntree = _uiState.value.entree
        updateItem(entree, previousEntree)
    }

    fun updateSideDish(sideDish: SideDishItem) {
        val previousSideDish = _uiState.value.sideDish
        updateItem(sideDish, previousSideDish)
    }

    fun updateAccompaniment(accompaniment: AccompanimentItem) {
        val previousAccompaniment = _uiState.value.accompainment
        updateItem(accompaniment, previousAccompaniment)
    }

    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    private fun updateItem(newItem: MenuItem, previousItem: MenuItem?) {
        _uiState.update { currentState ->
            val previousItemPrice = previousItem?.price ?: 0.0
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
            val tax = itemTotalPrice * taxRate
            currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax,
                entree = newItem as? EntreeItem ?: currentState.entree,
                sideDish = newItem as? SideDishItem ?: currentState.sideDish,
                accompainment =
                    newItem as? AccompanimentItem ?: currentState.accompainment
            )
        }
    }
}

fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}