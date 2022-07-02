package pl.cleankod.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import pl.cleankod.exchange.core.gateway.CacheService;
import pl.cleankod.exchange.core.gateway.CurrencyConversionService;
import pl.cleankod.exchange.core.usecase.ConvertAccountCurrencyService;
import pl.cleankod.exchange.core.usecase.ConvertAccountCurrencyServiceImpl;
import pl.cleankod.exchange.provider.CurrencyConversionServiceImpl;
import pl.cleankod.exchange.provider.nbp.ExchangeRatesNbpClient;
import pl.cleankod.exchange.provider.nbp.model.RateWrapper;

import java.util.Currency;
import java.util.Optional;

@Configuration
public class ConvertCurrencyConfiguration {
    @Bean
    CurrencyConversionService currencyConversionNbpService(ExchangeRatesNbpClient exchangeRatesNbpClient,
                                                           CacheService<String, Optional<RateWrapper>> cacheService) {
        return new CurrencyConversionServiceImpl(exchangeRatesNbpClient, cacheService);
    }

    @Bean
    ConvertAccountCurrencyService convertAccountCurrencyService(
            @Autowired CurrencyConversionService currencyConversionService,
            @Autowired Environment environment) {
        Currency baseCurrency = Currency.getInstance(environment.getRequiredProperty("app.base-currency"));
        return new ConvertAccountCurrencyServiceImpl(currencyConversionService, baseCurrency);
    }
}