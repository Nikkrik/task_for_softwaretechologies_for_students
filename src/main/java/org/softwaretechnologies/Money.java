package org.softwaretechnologies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

public class Money {
    private final MoneyType type;
    private final BigDecimal amount;

    public Money(MoneyType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Money равны, если одинаковый тип валют и одинаковое число денег до 4 знака после запятой.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @param o объект для сравнения
     * @return true - равно, false - иначе
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        if(!type.equals(money.type)) return false;

        BigDecimal thisRounded = amount.setScale(4, RoundingMode.HALF_UP);
        BigDecimal thatRounded = money.amount.setScale(4, RoundingMode.HALF_UP);

        return thisRounded.equals(thatRounded);
    }

    /**
     * Формула:
     * (Если amount null 10000, иначе количество денег окрукленные до 4х знаков * 10000) + :
     * если USD , то 1
     * если EURO, то 2
     * если RUB, то 3
     * если KRONA, то 4
     * если null, то 5
     * Если amount округленный до 4х знаков * 10000 >= (Integer.MaxValue - 5), то хеш равен Integer.MaxValue
     * Округление по правилу: если >= 5, то в большую сторону, иначе - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @return хеш код по указанной формуле
     */
    @Override
    public int hashCode() {
        BigDecimal roundedAmount = amount != null ?
                amount.setScale(4, RoundingMode.HALF_UP) : null;

        // Вычисляем первую часть хеша: округленное количество * 10000
        long amountHash = 0;
        if (roundedAmount != null) {
            // Умножаем на 10000 и конвертируем в long
            amountHash = roundedAmount.multiply(BigDecimal.valueOf(10000)).longValue();
        } else {
            amountHash = 10000; // если amount null
        }

        // Определяем код валюты
        int currencyCode = 5; // по умолчанию для null типа
        if (type != null) {
            switch (type) {
                case USD: currencyCode = 1; break;
                case EURO: currencyCode = 2; break;
                case RUB: currencyCode = 3; break;
                case KRONA: currencyCode = 4; break;
            }
        }

        // Вычисляем итоговый хеш
        long totalHash = amountHash + currencyCode;

        // Проверяем на переполнение
        if (totalHash >= (long) MAX_VALUE - 5) {
            return MAX_VALUE;
        }

        return (int) totalHash;

    }

    /**
     * Верните строку в формате
     * Тип_ВАЛЮТЫ: количество.XXXX
     * Тип_валюты: USD, EURO, RUB или KRONA
     * количество.XXXX - округленный amount до 4х знаков.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     * <p>
     * Если тип валюты null, то вернуть:
     * null: количество.XXXX
     * Если количество денег null, то вернуть:
     * Тип_ВАЛЮТЫ: null
     * Если и то и то null, то вернуть:
     * null: null
     *
     * @return приведение к строке по указанному формату.
     */
    @Override
    public String toString() {
        String typeStr = (type == null) ? "null" : type.toString();
        String amountStr = (amount == null) ? "null" : amount.setScale(4, RoundingMode.HALF_UP).toString();

        return typeStr + ": " + amountStr;

    }

    public BigDecimal getAmount() {
        return amount;
    }

    public MoneyType getType() {
        return type;
    }

    public static void main(String[] args) {
        Money money = new Money(MoneyType.EURO, BigDecimal.valueOf(10.00012));
        Money money1 = new Money(MoneyType.USD, BigDecimal.valueOf(10.5000));
        System.out.println(money1.toString());
        System.out.println(money1.hashCode());
        System.out.println(money.equals(money1));
    }
}
