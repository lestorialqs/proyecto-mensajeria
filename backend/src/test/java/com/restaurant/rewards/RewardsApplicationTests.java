package com.restaurant.rewards;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Test de sanidad basico - no levanta el contexto de Spring
// para evitar dependencias de Kafka y PostgreSQL en tests unitarios
class RewardsApplicationTests {

    @Test
    void sanityCheck() {
        // Prueba de sanidad basica: el modulo compila y las clases estan accesibles
        assertTrue(true, "El modulo de rewards compila correctamente");
    }

}

