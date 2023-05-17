package pl.ignacbartosz.bank.common.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.Arrays;

public class ControllerUtilsTest {

    @Test
    void shouldNotGeneratePageNumbersForEmptyList() {
        // given
        Page page = Mockito.mock(Page.class);
        Mockito.when(page.getTotalPages()).thenReturn(0);

        Model model = Mockito.mock(Model.class);

        // when
        ControllerUtils.paging(model, page);

        // then
        Mockito.verifyNoInteractions(model);
        Mockito.verify(model, Mockito.times(0)).addAttribute(Mockito.anyString(), Mockito.any());
    }

    @Test
    void shouldGeneratePageNumbers() {
        // given
        Page page = Mockito.mock(Page.class);
        Mockito.when(page.getTotalPages()).thenReturn(3);

        Model model = Mockito.mock(Model.class);

        // when
        ControllerUtils.paging(model, page);

        // then
        Mockito.verify(model).addAttribute(Mockito.any(), Mockito.any());
        Mockito.verify(model).addAttribute(Mockito.anyString(), Mockito.any());
        Mockito.verify(model).addAttribute(Mockito.eq("pageNumbers"), Mockito.eq(Arrays.asList(1, 2, 3)));
    }


}
