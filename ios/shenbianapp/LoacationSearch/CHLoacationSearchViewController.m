//
//  CHLoacationSearchViewController.m
//  shenbianapp
//
//  Created by book on 2017/11/10.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHLoacationSearchViewController.h"
#import <AMapSearchKit/AMapSearchKit.h>
#import <AMapFoundationKit/AMapFoundationKit.h>
#import <AMapLocationKit/AMapLocationKit.h>
@interface CHLoacationSearchViewController ()<UITableViewDelegate,UITableViewDataSource,AMapSearchDelegate,AMapLocationManagerDelegate>
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,strong)NSMutableArray *locationList;
@property(nonatomic,strong)AMapSearchAPI *search;
@property(nonatomic,strong)AMapLocationManager *locationManager;
@property(nonatomic,assign)NSInteger selectedRow;
@end

@implementation CHLoacationSearchViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self.rightButton setTitle:@"确认" forState:(UIControlStateNormal)];
    self.rightButton.hidden = NO;
    
    
}

-(void)bindViewControllerModel{
    
    self.locationList =  [NSMutableArray array];

    [self configLocationManager];
    [self locateAction];
    
    self.search = [[AMapSearchAPI alloc] init];
    self.search.delegate = self;
        
    
    
    
}

/* POI 搜索回调. */
- (void)onPOISearchDone:(AMapPOISearchBaseRequest *)request response:(AMapPOISearchResponse *)response
{
    if (response.pois.count == 0)
    {
        return;
    }
    
    for (AMapPOI *poi in response.pois) {
        [self.locationList addObject:[NSString stringWithFormat:@"%@,%@,%@",poi.city,poi.district,poi.name]];
    }
    [self.tableView reloadData];
    //解析response获取POI信息，具体解析见 Demo
}



- (void)configLocationManager
{
    
    self.locationManager = [[AMapLocationManager alloc] init];

    [self.locationManager setDelegate:self];

    [self.locationManager setDesiredAccuracy:kCLLocationAccuracyHundredMeters];

    [self.locationManager setLocationTimeout:6];

    [self.locationManager setReGeocodeTimeout:3];
}

- (void)locateAction
{
    //带逆地理的单次定位
    [self.locationManager requestLocationWithReGeocode:YES completionBlock:^(CLLocation *location, AMapLocationReGeocode *regeocode, NSError *error) {

        if (error)
        {
            NSLog(@"locError:{%ld - %@};", (long)error.code, error.localizedDescription);

            if (error.code == AMapLocationErrorLocateFailed)
            {
                return;
            }
        }

        //定位信息
        NSLog(@"location:%@", location);
        [self.locationList addObject:[NSString stringWithFormat:@"%@,%@,%@,%@,%@",regeocode.city,regeocode.district,regeocode.street,regeocode.POIName,regeocode.number]];
        //逆地理信息
        if (regeocode)
        {
            NSLog(@"reGeocode:%@", regeocode);
            AMapPOIKeywordsSearchRequest *request = [[AMapPOIKeywordsSearchRequest alloc] init];
            request.keywords            = regeocode.POIName;
            request.city                = regeocode.city;
            request.types               = @"服务";
            request.requireExtension    = YES;
            request.cityLimit           = YES;
            request.requireSubPOIs      = YES;
            [self.search AMapPOIKeywordsSearch:request];
        }
    }];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.tabBarController.tabBar.hidden = YES;
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    self.tabBarController.tabBar.hidden = NO;
    
}

-(void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];
    NSIndexPath *indexPath = [NSIndexPath indexPathForRow:0 inSection:0];
    UITableViewCell *cell = [self.tableView cellForRowAtIndexPath:indexPath];
    cell.textLabel.textColor = [UIColor colorWithHexString:@"#009698"];

}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}



-(void)setupViews{
 
    [self.view addSubview:self.tableView];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.equalTo(self.view);
        make.top.equalTo(self.view).offset(64);
        make.bottom.equalTo(self.view).offset(0);
    }];
}

- (UITableView *)tableView{

    if (_tableView == nil) {
        _tableView = [UITableView new];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        [_tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:@"locationCell"];
        _tableView.tableFooterView = [UIView new];
        _tableView.separatorColor = [UIColor colorWithHexString:@"#ebebeb"];
    }
    return _tableView;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.locationList.count;
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"locationCell"];
    cell.textLabel.text = self.locationList[indexPath.row];
    cell.textLabel.textColor = [UIColor colorWithHexString:@"#2d333a;"];
    cell.textLabel.font = [UIFont systemFontOfSize:15];
    cell.textLabel.textAlignment = NSTextAlignmentCenter;
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.textLabel.numberOfLines = 0;
    if (indexPath.row == self.selectedRow) {
        cell.textLabel.textColor = [UIColor colorWithHexString:@"#009698"];
    }
    return cell;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    UITableViewCell *cell = [tableView cellForRowAtIndexPath:indexPath];
    for (UILabel *label in tableView.visibleCells) {
        label.textColor = [UIColor colorWithHexString:@"#2d333a;"];
    }
    cell.textLabel.textColor = [UIColor colorWithHexString:@"#009698"];

    NSString *detailAddress = self.locationList[indexPath.row];
    [[NSNotificationCenter defaultCenter] postNotificationName:kCHNotificationDetailAddress object:detailAddress];
}

-(void)clickRightButton{
    [self.navigationController popViewControllerAnimated:YES];
    NSString *detailAddress = self.locationList[0];
    [[NSNotificationCenter defaultCenter] postNotificationName:kCHNotificationDetailAddress object:detailAddress];
}

@end
