package com.javapid.service.graphql;

import com.javapid.entity.nivo.line.NivoLineAbstractData;
import com.javapid.service.PidCouponsService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.javapid.service.graphql.GraphQLService.generateParametersData;

@Component
public class NivoLineDataFetcher implements DataFetcher<List<NivoLineAbstractData>> {

	private final PidCouponsService couponsService;

	public NivoLineDataFetcher(PidCouponsService couponsService) {
		this.couponsService = couponsService;
	}

	@Override
	public List<NivoLineAbstractData> get(DataFetchingEnvironment dataFetchingEnvironment) {
		return couponsService.getNivoLineData(generateParametersData(dataFetchingEnvironment));
	}
}
