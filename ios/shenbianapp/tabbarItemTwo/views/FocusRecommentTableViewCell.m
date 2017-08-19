//
//  FocusRecommentTableViewCell.m
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/15.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import "FocusRecommentTableViewCell.h"
#import "RecommentCardCell.h"
@implementation FocusRecommentTableViewCell
- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        self.backgroundColor = [UIColor colorWithHexColor:@"#f0f0f0"];
        [self addSubview:self.recommentContentView];
        [self.recommentContentView addSubview:self.titleName];
        [self.recommentContentView addSubview:self.recomentCollecttion];
        
    }
    return self;
}

- (UIView*)recommentContentView{
    if (!_recommentContentView) {
        _recommentContentView = [[UIView alloc]initWithFrame:CGRectMake(0, 10, kScreenWidth, 195)];
        _recommentContentView.backgroundColor = [UIColor whiteColor];
    }
    return _recommentContentView;
}
- (UILabel*)titleName{
    if (!_titleName) {
        _titleName = [[UILabel alloc]initWithFrame:CGRectMake((kScreenWidth-100)/2.0, 15, 100, 15)];
        _titleName.textAlignment = NSTextAlignmentCenter;
        _titleName.textColor = [UIColor colorWithHexColor:@"#1d1d1d"];
        _titleName.font = [UIFont systemFontOfSize:13.0];
        _titleName.text = @"相关推荐";
    }
    return _titleName;
}

- (UICollectionView*)recomentCollecttion{
    if (!_recomentCollecttion) {
        UICollectionViewFlowLayout *flowLayOut = [[UICollectionViewFlowLayout alloc]init];
        flowLayOut.scrollDirection = UICollectionViewScrollDirectionHorizontal;
        flowLayOut.itemSize = CGSizeMake(80, 130);
        flowLayOut.minimumInteritemSpacing = 10;
        flowLayOut.minimumLineSpacing = 10;
        flowLayOut.sectionInset = UIEdgeInsetsMake(0, 10, 0, 10);
        _recomentCollecttion = [[UICollectionView alloc] initWithFrame:CGRectMake(0,self.titleName.y+self.titleName.height+20, kScreenWidth, 130) collectionViewLayout:flowLayOut];
        _recomentCollecttion.backgroundColor = [UIColor colorWithHexColor:@"#ffffff"];
        _recomentCollecttion.delegate = self;
        _recomentCollecttion.dataSource = self;
        _recomentCollecttion.showsHorizontalScrollIndicator = NO;
        _recomentCollecttion.showsVerticalScrollIndicator = NO;
        [_recomentCollecttion registerClass:[RecommentCardCell class] forCellWithReuseIdentifier:@"RecommentCardCell"];
    }
    return _recomentCollecttion;
}


- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
        return 5;
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    RecommentCardCell  * cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"RecommentCardCell" forIndexPath:indexPath];
    return cell;
}

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
