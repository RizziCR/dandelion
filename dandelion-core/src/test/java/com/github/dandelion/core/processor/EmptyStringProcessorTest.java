package com.github.dandelion.core.processor;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;

import com.github.dandelion.core.Context;
import com.github.dandelion.core.GlobalOptionsRule;
import com.github.dandelion.core.option.DefaultOptionProcessingContext;
import com.github.dandelion.core.option.EmptyStringProcessor;
import com.github.dandelion.core.option.Option;
import com.github.dandelion.core.option.OptionProcessingContext;
import com.github.dandelion.core.option.OptionProcessor;
import com.github.dandelion.core.option.StringProcessor;
import com.github.dandelion.core.web.WebConstants;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyStringProcessorTest {

   private HttpServletRequest request;
   private Option<String> option = new Option<String>("optionName", new StringProcessor(), 0);

   @Rule
   public GlobalOptionsRule options = new GlobalOptionsRule();
   
   @Before
   public void setup() {
      request = new MockHttpServletRequest();
      request.setAttribute(WebConstants.DANDELION_CONTEXT_ATTRIBUTE, new Context(new MockFilterConfig()));
   }

	@Test
	public void should_update_the_table_entry_with_an_empty_string() throws Exception{
		Entry<Option<?>, Object> entry = new MapEntry<Option<?>, Object>(option, "");
		OptionProcessor processor = new EmptyStringProcessor();
      OptionProcessingContext pc = new DefaultOptionProcessingContext(entry, request, processor.isBundleGraphUpdatable());
		processor.process(pc);
		assertThat(entry.getValue()).isEqualTo("");
	}
	
	@Test
	public void should_update_the_column_entry_with_an_empty_string() throws Exception{
		Entry<Option<?>, Object> entry = new MapEntry<Option<?>, Object>(option, "");
		OptionProcessor processor = new EmptyStringProcessor();
      OptionProcessingContext pc = new DefaultOptionProcessingContext(entry, request, processor.isBundleGraphUpdatable());
		processor.process(pc);
		assertThat(entry.getValue()).isEqualTo("");
	}
}