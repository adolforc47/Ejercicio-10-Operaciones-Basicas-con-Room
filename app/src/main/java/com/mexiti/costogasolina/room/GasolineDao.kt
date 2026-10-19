package com.mexiti.costogasolina.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mexiti.costogasolina.model.GasolineTransaction
import kotlinx.coroutines.flow.Flow

@Dao
interface GasolineDao {

    // Agregar un registro
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: GasolineTransaction)

    // Mostrar todos los registros
    @Query("SELECT * FROM gasoline_transactions ORDER BY date DESC")
    fun getAlltransactions(): Flow<List<GasolineTransaction>>

    // Buscar un registro por su ID
    @Query("SELECT * FROM gasoline_transactions WHERE id = :id")
    suspend fun getTransactionById(id: Int): GasolineTransaction?

    // Modificar un registro existente
    @Update
    suspend fun updateTransaction(transaction: GasolineTransaction)

    // Eliminar un registro
    @Delete
    suspend fun deleteTransaction(transaction: GasolineTransaction)
}
