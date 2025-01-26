package dz.protid.it.reporting;

import dz.protid.it.domain.Invoice;
import dz.protid.it.domain.Order;
import dz.protid.it.domain.Product;
import dz.protid.it.reporting.template.AbstractReport;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public class ReportingServiceImpl extends AbstractReport {

    private final Invoice invoice;

    public ReportingServiceImpl(Object invoice) {
        this.invoice = (Invoice) invoice;
    }

    @Override
    public JasperReportBuilder build() throws DRException {
        return report()
                .setTemplate(AbstractReport.reportTemplate)
                .title(createTitleComponent())
                .pageHeader(createPageHeaderComponent())
                .pageFooter(cmp.pageXofY())
                .detail(createDetailComponent())
                .setDataSource(createDummyDataSource());
    }

    @Override
    protected ComponentBuilder<?, ?> createTitleComponent() {
        return cmp.verticalList(
                cmp.text("DEPOT " + invoice.getMarqueName())
                        .setStyle(stl.style().bold().setFontSize(18)
                                .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)),
                cmp.verticalGap(10));
    }

    @Override
    protected ComponentBuilder<?, ?> createPageHeaderComponent() {
        return cmp.verticalList(
                cmp.horizontalList()
                        .setStyle(createHeaderStyle())
                        .add(preparationInfoComponent())
                        .add(formattedDateComponent(formatCurrentTimestamp()))
                        .newRow().add(cmp.verticalGap(5)),
                marqueInfoComponent()
        );
    }

    @Override
    protected ComponentBuilder<?, ?> createDetailComponent() {
        VerticalListBuilder detailComponent = cmp.verticalList();
        groupProductsByName().forEach((productName, variants) -> {
            VerticalListBuilder productGroup = createProductGroup(productName);
            variants.forEach(product -> productGroup.add(createProductVariant(product)).add(cmp.verticalGap(10)));
            detailComponent.add(productGroup).add(cmp.verticalGap(15));
        });
        return detailComponent;
    }

    private Map<String, List<Product>> groupProductsByName() {
        return invoice.getProducts().stream()
                .collect(Collectors.groupingBy(Product::getProductName));
    }

    private VerticalListBuilder createProductGroup(String productName) {
        return cmp.verticalList()
                .setStyle(stl.style().setPadding(15))
                .add(cmp.text("Product: " + productName)
                        .setStyle(stl.style().bold().setPadding(5)));
    }

    private ComponentBuilder<?, ?> createProductVariant(Product product) {
        VerticalListBuilder categoryBox = cmp.verticalList()
                .setStyle(createBorderedStyle())
                .add(categoryHeaderComponent(product.getCategory()));

        product.getOrders().forEach(order -> categoryBox.add(createOrderLine(order)));
        return categoryBox.add(createTotalComponent(product));
    }

    private ComponentBuilder<?, ?> createOrderLine(Order order) {
        // Define the maximum line width (in characters)
        int maxLineWidth = 75; // Adjust based on your layout

        // Create the prefix and suffix
        String prefix = "BL N:" + order.getBlNumber() + "    " + order.getClient().getName()+"  ";
        String suffix = "     " + order.getQuantitySold();

        // Calculate the number of dots needed
        int dotsLength = maxLineWidth - (prefix.length() + suffix.length());

        // Ensure dotsLength is not negative
        if (dotsLength < 0) {
            dotsLength = 0;
        }

        // Generate the dots
        String dots = ".".repeat(dotsLength);

        // Combine the prefix, dots, and suffix
        String line = prefix + dots +" \u25A1"+ suffix ;

        return cmp.text(line)
                .setStyle(stl.style()
                        .setPadding(3)
                        .setFontSize(11));
    }
    private ComponentBuilder<?, ?> createTotalComponent(Product product) {
        return cmp.verticalList()
                .add(cmp.verticalGap(5))
                .add(cmp.text(String.format("Product Total: %s  %s : %d",
                                product.getProductCode(),
                                product.getCategory(),
                                calculateProductTotal(product)))
                        .setStyle(stl.style().bold())
                        .setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT));
    }

    private int calculateProductTotal(Product product) {
        return product.getOrders().stream()
                .mapToInt(Order::getQuantitySold)
                .sum();
    }

    private ComponentBuilder<?, ?> preparationInfoComponent() {
        return cmp.text("Bon de preparation : " + invoice.getLivreur())
                .setStyle(stl.style().setFontSize(12));
    }

    private ComponentBuilder<?, ?> formattedDateComponent(String date) {
        return cmp.text(date)
                .setStyle(stl.style().setFontSize(12))
                .setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT);
    }

    private ComponentBuilder<?, ?> marqueInfoComponent() {
        return cmp.text("Marque " + invoice.getMarqueName())
                .setStyle(stl.style().setFontSize(10)
                        .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT));
    }

    private ComponentBuilder<?, ?> categoryHeaderComponent(String category) {
        return cmp.text("Category: " + category)
                .setStyle(stl.style().bold().setPadding(5));
    }
}
