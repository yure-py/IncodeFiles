package org.example.Study.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Pool {
    private static final String DB_INFO_FILENAME = "databaseInfo.properties";
    private static final String ENV = "dev";
    private static final HikariDataSource dataSource;

    static {
        try (
            InputStream input = ClassLoader.getSystemResourceAsStream(DB_INFO_FILENAME);
        ) {
            /*logger*/ System.out.println("Configurado hardcoded para o ambiente de DESENVOLVIMENTO...");
            /*logger*/ System.out.println("Iniciando configuração da pool...");

            if (input == null) {
                throw new IllegalStateException("Arquivo " + DB_INFO_FILENAME + " não encontrado!");
            }

            Properties p = new Properties();

            /*logger*/ System.out.println("Carregando informações para login...");
            p.load(input);

            validateProperties(p);

            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(p.getProperty(ENV + ".url"));
            config.setUsername(p.getProperty(ENV + ".user"));
            config.setPassword(p.getProperty(ENV + ".password"));

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setIdleTimeout(30000);
            config.setConnectionTimeout(10000);
            config.setLeakDetectionThreshold(5000);

            /*logger*/ System.out.println("Pool de conexões configurada com sucesso...");

            dataSource = new HikariDataSource(config);

            /*logger*/ System.out.println("Pool de conexões inicializado com sucesso...");
        }
        catch (Exception e) {
            /*logger*/ System.out.println("Erro ao configurar o pool de conexões: " + e.getMessage());
            throw new RuntimeException("Erro ao configurar o pool de conexões!", e);
        }
    }

    private Pool() {
        // Construtor privado para evitar instâncias
    }

    private static void validateProperties(Properties p) {
        if (!p.containsKey(ENV + ".url") || !p.containsKey(ENV + ".user") || !p.containsKey(ENV + ".password")) {
            throw new IllegalStateException("Propriedades do banco de dados estão incompletas!");
        }
    }

    public static Connection getConnection() {
        try {
            /*logger*/ System.out.println("Obtendo conexão com o banco de dados...");
            Connection c = dataSource.getConnection();
            /*logger*/ System.out.println("Conexão bem sucedida...");
            return c;
        } catch (SQLException e) {
            /*logger*/ System.out.println ("Erro ao obter conexão: " + e.getMessage());
            throw new RuntimeException("Erro ao conectar no banco de dados!", e);
        }
    }

    public static void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
