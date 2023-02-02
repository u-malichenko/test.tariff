package ru.malichenko.test.filter;

import lombok.Data;

@Data
public class TariffFilter {
    private TariffField title;
    private TariffField isArchived;
    private TariffField unlimitedVoice;
    private TariffField unlimitedInternet;
}
